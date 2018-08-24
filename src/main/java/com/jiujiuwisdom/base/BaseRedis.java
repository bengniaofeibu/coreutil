package com.jiujiuwisdom.base;

public interface BaseRedis {

    /**
     *  设置
     * @param key  缓存key
     * @param value 缓存value
     * @return
     */
  String set(String key, String value);

    /**
     * 当key不存在的时候 设置key的值和过期时间
     * @param key 缓存key
     * @param value  缓存value
     * @param nx key不存在的时候才能set
     * @param ex 秒
     * @param time 时间
     * @return
     */
  String setnxAndExpire(String key, String value, String nx, String ex, long time);

    /**
     * 当key不在存在的时候 设置key的值
     * @param key 缓存key
     * @param value 缓存value
     * @return
     */
  String setnx(String key, String value);

    /**
     *  当key在存在的时候 设置key的值并且设置过期时间
     * @param key 缓存key
     * @param seconds 获取时间 秒
     * @param value  缓存value
     * @return
     */
  String setex(String key, int seconds, String value);



    void close();
}
