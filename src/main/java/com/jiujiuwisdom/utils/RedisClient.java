package com.jiujiuwisdom.utils;

import redis.clients.jedis.Jedis;

public class RedisClient {

    /**
     *  设置
     * @param key  缓存key
     * @param value 缓存value
     * @return
     */
   public static String set(String key, String value){
       Jedis jedis = RedisUtil.getJedis();
       return jedis.set(key, value);
   }

    /**
     * 当key不存在的时候 设置key的值和过期时间
     * @param key 缓存key
     * @param value  缓存value
     * @param time 过期时间
     * @return
     */
    public static String setnxAndExpire(String key, String value, long time){
        return RedisUtil.getJedis().set(key, value,"nx","ex",time);
    }

    /**
     * 当key不在存在的时候 设置key的值
     * @param key 缓存key
     * @param value 缓存value
     * @return
     */
    public long setnx(String key, String value){
        return RedisUtil.getJedis().setnx(key,value);
    }

    /**
     *  当key在存在的时候 设置key的值并且设置过期时间
     * @param key 缓存key
     * @param seconds 获取时间 秒
     * @param value  缓存value
     * @return
     */
    public String setex(String key, int seconds, String value){
        return RedisUtil.getJedis().setex(key,seconds,value);
    }
}
