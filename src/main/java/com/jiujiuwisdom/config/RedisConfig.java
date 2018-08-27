package com.jiujiuwisdom.config;

import com.jiujiuwisdom.utils.RedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.locks.ReentrantLock;

public class RedisConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisConfig.class);

    private static final PropertiesConfig APPLICATION = new PropertiesConfig("bootstrap.yml");

    private static JedisPool JEDIS_POOL;

    private static final String REDIS_TEST_PROPERTIES = "redis/redis-test.properties";

    private static final String REDIS_PROD_PROPERTIES = "redis/redis-prod.properties";

    private static final String ACTIVE_PROD = "prod";

    private static final int MAX_TOTAL = 1024;

    private static final int MAX_IDLE = 500;

    private static final int MAX_WAIT_MILLIS = 10000;

    private static final int TIMEOUT = 5000;

    private static boolean TEST_ON_BORROW = true;

    private static boolean ACTIVE_FLAG;

    private static ReentrantLock LOCK_POOL = new ReentrantLock();

    private static ReentrantLock LOCK_JEDIS = new ReentrantLock();


   static {

       String profile = APPLICATION.getValue("profile");
       LOGGER.info("profile {}",profile);
       if (ACTIVE_PROD.equals(profile)) {
               ACTIVE_FLAG = true;
        }

        //初始化Jedis
        InitJedisPool();
    }

    private static void InitJedisPool() {


       //断言是否已经被锁住，没有就继续执行
       assert !LOCK_POOL.isHeldByCurrentThread();

        LOCK_POOL.lock();
        PropertiesConfig PROPERTIES;
        if (ACTIVE_FLAG) {

            PROPERTIES = new PropertiesConfig(REDIS_PROD_PROPERTIES);

        } else {

            PROPERTIES = new PropertiesConfig(REDIS_TEST_PROPERTIES);
        }

         String host = PROPERTIES.getValue("redis.host");

         int port = Integer.valueOf(PROPERTIES.getValue("redis.port"));

         String pwd = PROPERTIES.getValue("redis.pwd");

         int db = Integer.valueOf(PROPERTIES.getValue("redis.db"));


        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(MAX_TOTAL);
        config.setMaxIdle(MAX_IDLE);
        config.setMaxWaitMillis(MAX_WAIT_MILLIS);
        config.setTestOnBorrow(TEST_ON_BORROW);

        try {

            JEDIS_POOL = new JedisPool(config, host,port,TIMEOUT,pwd,db);
            LOGGER.info("host {}, port {}, db {}, jedisPool 初始化成功。。。", host,port,db);

        } catch (Exception e) {

            LOGGER.error("jedisPool 初始化失败 {}", e.getMessage());

        }finally {

            LOCK_POOL.unlock();
        }
    }

    public static Jedis getJedis(){

        //断言是否已经被锁住，没有就继续执行
        assert ! LOCK_JEDIS.isHeldByCurrentThread();

        LOCK_JEDIS.lock();

        if (JEDIS_POOL == null) {
            InitJedisPool();
        }
        Jedis jedis = null;
        try {
            if (JEDIS_POOL != null) {
                jedis = JEDIS_POOL.getResource();
            }
        } catch (Exception e) {
            LOGGER.error("获取 jedis error {}",e.getMessage());
        }finally{
            returnResource(jedis);
            LOCK_JEDIS.unlock();
        }
        return jedis;

    }

    /**
     * 释放jedis资源
     * @param jedis
     */
    public static void returnResource(final Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    public static void main(String[] args) {
        String set = RedisClient.set("aaa", "bbb");
        System.out.println(set);
    }
}
