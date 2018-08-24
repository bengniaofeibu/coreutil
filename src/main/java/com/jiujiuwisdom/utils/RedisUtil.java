package com.jiujiuwisdom.utils;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class RedisUtil {

    private static final PropertiesUtil APPLICATION = new PropertiesUtil("application.yml");

    private static JedisPool JEDIS_POOL;

    private static final String REDIS_TEST_PROPERTIES = "redis-test.properties";

    private static final String REDIS_PROD_PROPERTIES = "redis-prod.properties";

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

        if (ACTIVE_PROD.equals(APPLICATION.getValue("active"))) {
            ACTIVE_FLAG = true;
        }

        //初始化Jedis
        InitJedisPool();
    }

    private static void InitJedisPool() {

       //断言是否已经被锁住，没有就继续执行
       assert !LOCK_POOL.isHeldByCurrentThread();

        LOCK_POOL.lock();
        PropertiesUtil PROPERTIES;
        if (ACTIVE_FLAG) {

            PROPERTIES = new PropertiesUtil(REDIS_PROD_PROPERTIES);

        } else {

            PROPERTIES = new PropertiesUtil(REDIS_TEST_PROPERTIES);
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
            log.info("host {}, port {}, db {}, jedisPool 初始化成功。。。", host,port,db);

        } catch (Exception e) {

            log.error("jedisPool 初始化失败 {}", e.getMessage());

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
            log.error("获取 jedis error {}",e.getMessage());
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
        if (jedis != null && JEDIS_POOL !=null) {
            JEDIS_POOL.returnResource(jedis);
        }
    }

    public static void main(String[] args) {
        String set = RedisClient.set("aaa", "bbb");
        System.out.println(set);
    }
}
