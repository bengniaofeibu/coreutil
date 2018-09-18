package com.jiujiuwisdom.utils;

import com.jiujiuwisdom.config.RedisConfig;
import com.sun.org.apache.regexp.internal.RE;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class RedisClient {

    private RedisConfig redisConfig;

    public  RedisClient(String hostName,String portName,String pwdName,String dbName){

        redisConfig = new RedisConfig(hostName, portName, pwdName, dbName);
    }

    /**
     *  设置
     * @param key  缓存key
     * @param value 缓存value
     * @return
     */
   public String set(String key, String value){
       return redisConfig.getJedis().set(key, value);
   }

    /**
     *  同时设置一个或多个 key-value 对
     * @param keysvalues  缓存key
     * @return
     */
    public String mset(String...keysvalues){
        return redisConfig.getJedis().mset(keysvalues);
    }

    /**
     *  返回所有(一个或多个)给定 key 的值
     * @param keysvalues  缓存key
     * @return
     */
    public  List<String> mget(String...keysvalues){
        return redisConfig.getJedis().mget(keysvalues);
    }


    /**
     *  返回 key 所关联的字符串值
     * @param key
     * @return
     */
   public  String get(String key){
       return redisConfig.getJedis().get(key);
   }

    /**
     *  返回 key 中字符串值的子字符串，字符串的截取范围由 start 和 end 两个偏移量决定(包括 start 和 end 在内)。
     * @param key
     * @return
     */
    public  String get(String key,long start,long end){
        return redisConfig.getJedis().getrange(key,start,end);
    }

    /**
     *  删除一个或多个key
     * @param key
     * @return
     */
   public  long del(String...key){
       return redisConfig.getJedis().del(key);
   }

    /**
     *  判断一个 或多个key是否存在
     * @param key
     * @return
     */
   public  boolean exists(String key){
        return redisConfig.getJedis().exists(key);
   }


    /**
     *  将 key 中储存的数字值增一
     * @param key
     * @return
     */
   public  Long incr(String key){
      return redisConfig.getJedis().incr(key);
   }

    /**
     *  将 key 所储存的值加上增量 increment
     * @param key
     * @return
     */
    public  Long incrBy(String key,long increment){
        return redisConfig.getJedis().incrBy(key,increment);
    }


    /**
     *  将 key 中储存的数字值减一
     * @param key
     * @return
     */
    public  Long decr(String key){
        return redisConfig.getJedis().decr(key);
    }

    /**
     *  将 key 中储存的数字值减量 increment
     * @param key
     * @param increment
     * @return
     */
    public  Long decrBy(String key,long increment){
        return redisConfig.getJedis().decrBy(key,increment);
    }



    /**
     *  将 key 所储存的值加上增量 increment
     * @param key
     * @return
     */
    public  Long append(String key,String value){
        return redisConfig.getJedis().append(key,value);
    }



    /**
     * 获取旧值并设置新值
     * @param key
     * @param value
     * @return
     */
   public  String getSet(String key,String value){
       return redisConfig.getJedis().getSet(key,value);
   }

    /**
     * 当key不存在的时候 设置key的值和过期时间
     * @param key 缓存key
     * @param value  缓存value
     * @param time 过期时间
     * @return
     */
    public  String setnxAndExpire(String key, String value, long time){
        return redisConfig.getJedis().set(key, value,"nx","ex",time);
    }

    /**
     * 当key不在存在的时候 设置key的值
     * @param key 缓存key
     * @param value 缓存value
     * @return
     */
    public  Long setnx(String key, String value){
        return redisConfig.getJedis().setnx(key,value);
    }

    /**
     *  当key存在的时候 设置key的值并且设置过期时间
     * @param key 缓存key
     * @param seconds 获取时间 秒
     * @param value  缓存value
     * @return
     */
    public  String setexAndExpire(String key,String value, int seconds){
        return redisConfig.getJedis().setex(key,seconds,value);
    }


    /**
     *  将哈希表 key 中的域 field 的值设为 value 。
     * @param key 缓存key
     * @param field  hash key
     * @param value hash value
     * @return
     */
    public  Long hset(String key,String field,String value){
        return redisConfig.getJedis().hset(key,field,value);
    }

    /**
     * 将哈希表 key 中的域 field 的值设置为 value ，当且仅当域 field 不存在。
     * @param key 缓存key
     * @param field hash key
     * @param value hash value
     * @return
     */
    public  Long hsetnx(String key,String field,String value){
        return redisConfig.getJedis().hsetnx(key,field,value);
    }

    /**
     *  同时将多个 field-value (域-值)对设置到哈希表 key 中。
     * @param key
     * @param hash
     * @return
     */
    public  String hmset(String key, Map<String,String> hash){
       return redisConfig.getJedis().hmset(key,hash);
    }

    /**
     *  返回哈希表 key 中给定域 field 的值
     * @return
     */
    public  String hget(String key,String field){
        return redisConfig.getJedis().hget(key,field);
    }

    /**
     *  返回哈希表 key 中，一个或多个给定域的值。
     * @param key
     * @param fields
     * @return
     */
    public  List<String> hmget(String key,String...fields){
        return redisConfig.getJedis().hmget(key,fields);
    }

    /**
     *
     *   为哈希表 key 中的域 field 的值加上增量 increment 。
     *   增量也可以为负数，相当于对给定域进行减法操作。
     *   如果 key 不存在，一个新的哈希表被创建并执行 HINCRBY 命令。
     *   如果域 field 不存在，那么在执行命令前，域的值被初始化为 0 。
     *   对一个储存字符串值的域 field 执行 HINCRBY 命令将造成一个错误。
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public  Long hincrBy(String key,String field,long value){
          return redisConfig.getJedis().hincrBy(key,field,value);
    }

    /**
     *   删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
     * @param key
     * @param field
     * @return
     */
    public Long hdel(String key,String...field){
        return redisConfig.getJedis().hdel(key,field);
    }

    /**
     * 查看哈希表 key 中，给定域 field 是否存在。
     * @param key
     * @param field
     * @return
     */
    public boolean hexists(String key, String field){
        return redisConfig.getJedis().hexists(key,field);
    }


    /**
     * 返回哈希表 key 中的所有域
     * @param key
     * @return
     */
    public  Set<String> hkeys(String key){
        return redisConfig.getJedis().hkeys(key);
    }

    /**
     * 返回哈希表 key 中所有域的值。
     * @param key
     * @return
     */
    public  List<String> hvals(String key){
        return redisConfig.getJedis().hvals(key);
    }

    /**
     * 返回哈希表 key 中，所有的域和值。
     * @param key
     * @return
     */
    public  Map<String,String> hgetAll(String key){
       return redisConfig.getJedis().hgetAll(key);

    }

    /**
     *  将一个或多个值 value 插入到列表 key 的表头
     * @param key
     * @param strings
     * @return 列表的长度
     */
    public Long lpush(String key,String...strings){
      return redisConfig.getJedis().lpush(key,strings);
    }

    /**
     *   移除并返回列表 key 的头元素。
     *
     * @param key
     * @return  列表的头元素  当 key 不存在时，返回 nil
     */
    public String lpop(String key){
        return redisConfig.getJedis().lpop(key);
    }

    /**
     *  将一个或多个值 value 插入到列表 key 的表尾(最右边)
     * @param key
     * @param strings
     * @return 列表的长度
     */
    public Long rpush(String key,String...strings){
       return redisConfig.getJedis().rpush(key,strings);
    }

    /**
     *  移除并返回列表 key 的尾元素
     * @param key
     * @return 列表的尾元素 当 key 不存在时，返回 nil
     */
    public String rpop(String key){
       return redisConfig.getJedis().rpop(key);
    }

    /**
     *  将列表 source 中的最后一个元素(尾元素)弹出，并返回给客户端
     *  将 source 弹出的元素插入到列表 destination ，作为 destination 列表的的头元素
     * @param srckey
     * @param dstkey
     * @return
     */
    public String rpoplpush(String srckey,String dstkey){
      return redisConfig.getJedis().rpoplpush(srckey,dstkey);
    }

    /**
     *   将列表 key 下标为 index 的元素的值设置为 value
     * @param key
     * @param index
     * @param value
     * @return
     */
    public String lset(String key,long index,String value){
      return redisConfig.getJedis().lset(key, index, value);
    }

    /**
     *  根据参数 count 的值，移除列表中与参数 value 相等的元素
     * @param key
     * @param count count > 0 : 从表头开始向表尾搜索，移除与 value 相等的元素，数量为 count
     *               count < 0 : 从表尾开始向表头搜索，移除与 value 相等的元素，数量为 count 的绝对值
     *               count = 0 : 移除表中所有与 value 相等的值
     *
     * @param value
     * @return
     */
    public long lrem(String key,long count,String value){
     return redisConfig.getJedis().lrem(key, count, value);
    }

    /**
     *  让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除
     * @param key
     * @param start
     * @param end
     * @return
     */
    public String ltrim(String key,long start,long end){
      return redisConfig.getJedis().ltrim(key, start, end);
    }

    /**
     *  返回列表 key 中指定区间内的元素，区间以偏移量 start 和 stop 指定
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<String> lrange(String key,long start,long end){
      return redisConfig.getJedis().lrange(key, start, end);
    }

    /**
     *  返回列表 key 的长度
     * @param key
     * @return
     */
    public Long llen(String key){
        return redisConfig.getJedis().llen(key);
    }

    /**
     *   返回列表 key 中，下标为 index 的元素
     * @param key
     * @param index
     * @return
     */
    public  String lindex(String key,long index){
       return redisConfig.getJedis().lindex(key, index);
    }

    /**
     * 它是 LPOP 命令的阻塞版本，当给定列表内没有任何元素可供弹出的时候，连接将被 BLPOP 命令阻塞，直到等待超时或发现可弹出元素为止
     * 当给定多个 key 参数时，按参数 key 的先后顺序依次检查各个列表，弹出第一个非空列表的头元素
     *
     * @param keys
     * @return
     */
    public List<String> blpop(String...keys){
     return redisConfig.getJedis().blpop(keys);
    }


    /**
     * 它是 LPOP 命令的阻塞版本，当给定列表内没有任何元素可供弹出的时候，连接将被 BLPOP 命令阻塞，直到等待超时或发现可弹出元素为止
     * 当给定多个 key 参数时，按参数 key 的先后顺序依次检查各个列表，弹出第一个非空列表的头元素
     *
     * @param keys
     * @param timeout 等待超时时间
     * @return
     */
    public List<String> blpop(int timeout,String...keys){
        return redisConfig.getJedis().blpop(timeout,keys);
    }

    /**
     *  它是 RPOP 命令的阻塞版本，当给定列表内没有任何元素可供弹出的时候，连接将被 BRPOP 命令阻塞，直到等待超时或发现可弹出元素为止
     *  当给定多个 key 参数时，按参数 key 的先后顺序依次检查各个列表，弹出第一个非空列表的尾部元素
     *
     * @param keys
     * @return
     */
    public  List<String> brpop(String...keys){
        return redisConfig.getJedis().brpop(keys);
    }

    /**
     *  它是 RPOP 命令的阻塞版本，当给定列表内没有任何元素可供弹出的时候，连接将被 BRPOP 命令阻塞，直到等待超时或发现可弹出元素为止
     *  当给定多个 key 参数时，按参数 key 的先后顺序依次检查各个列表，弹出第一个非空列表的尾部元素
     *
     * @param keys
     * @param timeout 等待超时时间
     * @return
     */
    public  List<String> brpop(int timeout,String...keys){
        return redisConfig.getJedis().brpop(timeout,keys);
    }

    /**
     *  将一个或多个 member 元素加入到集合 key 当中，已经存在于集合的 member 元素将被忽略
     * @param key
     * @param members
     * @return
     */
    public Long sadd(String key,String...members){
       return redisConfig.getJedis().sadd(key,members);
    }

    /**
     * 返回一个集合的全部成员，该集合是所有给定集合之间的差集
     * @param keys
     * @return
     */
    public Set<String> sdiff(String...keys){
       return redisConfig.getJedis().sdiff(keys);
    }

    /**
     * 返回一个集合的全部成员，该集合是所有给定集合的交集
     * @param keys
     * @return
     */
    public  Set<String> sinter(String...keys){
       return redisConfig.getJedis().sinter(keys);
    }

    /**
     * 返回一个集合的全部成员，该集合是所有给定集合的并集
     * @param keys
     * @return
     */
    public Set<String> sunion (String...keys){
        return redisConfig.getJedis().sunion(keys);
    }



    /**
     *  判断 member 元素是否集合 key 的成员
     * @param key
     * @param member
     * @return
     */
    public boolean sismember(String key,String member){
        return redisConfig.getJedis().sismember(key,member);

    }

    /**
     *  返回集合 key 中的所有成员
     * @param key
     * @return
     */
    public  Set<String> smembers(String key){
        return redisConfig.getJedis().smembers(key);
    }

    /**
     * 将 member 元素从 source 集合移动到 destination 集合
     * @param srckey
     * @param dstkey
     * @param member
     * @return
     */
    public Long smove(String srckey,String dstkey,String member){
        return redisConfig.getJedis().smove(srckey,dstkey,member);

    }

    /**
     * 移除并返回集合中的一个随机元素
     * @param key
     * @return
     */
    public String spop(String key){
      return redisConfig.getJedis().spop(key);
    }

    /**
     *
     *  移除并返回集合中的count随机元素
     *
     * @param key
     * @param count 返回一个包含 count 个元素的数组，数组中的元素各不相同。如果 count 大于等于集合基数，那么返回整个集合。
     * @return
     */
    public Set<String> spop(String key,int count){
        return redisConfig.getJedis().spop(key,count);
    }

    /**
     * 那么返回集合中的一个随机元素
     * @param key
     * @return
     */
    public  String srandmember(String key){
        return redisConfig.getJedis().srandmember(key);
    }

    /**
     * 返回集合中的一个随机元素
     *
     * @param key
     * @param count 如果 count 为正数，且小于集合基数，那么返回一个包含 count 个元素的数组，数组中的元素各不相同。如果 count 大于等于集合基数，那么返回整个集合。
     *               如果 count 为负数，那么命令返回一个数组，数组中的元素可能会重复出现多次，而数组的长度为 count 的绝对值
     * @return
     */
    public List<String> srandmember(String key,int count){
        return redisConfig.getJedis().srandmember(key,count);
    }


    /**
     * 移除集合 key 中的一个或多个 member 元素，不存在的 member 元素会被忽略
     * @param key
     * @param member
     * @return
     */
    public Long srem(String key,String...member){
        return redisConfig.getJedis().srem(key,member);
    }

    /**
     * 将一个元素及其 score 值加入到有序集 key 当中
     * @param key
     * @param score
     * @param member
     * @return
     */
    public Long zadd(String key,double score,String member){
        return redisConfig.getJedis().zadd(key,score,member);
    }

    /**
     * 将一个或多个 member 元素及其 score 值加入到有序集 key 当中
     * @param key
     * @param members
     * @return
     */
    public Long zadd(String key,Map<String,Double> members){
        return redisConfig.getJedis().zadd(key,members);
    }

    /**
     * 返回有序集 key 中， score 值在 min 和 max 之间(默认包括 score 值等于 min 或 max )的成员的数量
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Long zadd(String key,double min,double max){
        return redisConfig.getJedis().zcount(key,min,max);
    }


    /**
     * 返回有序集 key 中， score 值在 min 和 max 之间(默认包括 score 值等于 min 或 max )的成员的数量
     * @param key
     * @param score
     * @param member
     * @return
     */
    public Double zincrby(String key,double score,String member){
        return redisConfig.getJedis().zincrby(key,score,member);
    }

    /**
     * 返回有序集 key 中，指定区间内的成员
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> zincrby(String key,long start,long end){
        return redisConfig.getJedis().zrange(key,start,end);
    }

    /**
     * 移除有序集 key 中，指定排名(rank)区间内的所有成员
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Long zrangeByScore(String key,int start,int end){
        return redisConfig.getJedis().zremrangeByRank(key,start,end);
    }


    /**
     * 返回有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员。有序集成员按 score 值递增(从小到大)次序排列
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> zrangeByScore(String key,double min,double max,int start,int end){
        return redisConfig.getJedis().zrangeByScore(key,min,max,start,end);
    }

    /**
     * 返回有序集 key 中， score 值介于 max 和 min 之间(默认包括等于 max 或 min )的所有的成员。有序集成员按 score 值递减(从大到小)的次序排列
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> zrevrangeByScore(String key,double max,double min,int start,int end){
        return redisConfig.getJedis().zrevrangeByScore(key,max,min,start,end);
    }

    /**
     * 移除有序集 key 中的一个或多个成员，不存在的成员将被忽略
     * @param key
     * @param member
     * @param member
     * @return
     */
    public Long zrangeByScore(String key,String...member){
        return redisConfig.getJedis().zrem(key,member);
    }

    /**
     * 返回有序集 key 中，成员 member 的 score 值
     * @param key
     * @param member
     * @param member
     * @return
     */
    public Double zscore(String key,String member){
        return redisConfig.getJedis().zscore(key,member);
    }

    /**
     * 返回有序集 key 中成员 member 的排名。其中有序集成员按 score 值递增(从小到大)顺序排列
     * @param key
     * @param member
     * @param member
     * @return
     */
    public Long zrank(String key,String member){
        return redisConfig.getJedis().zrank(key,member);
    }

    /**
     * 返回有序集 key 中成员 member 的排名。其中有序集成员按 score 值递减(从大到小)排序
     * @param key
     * @param member
     * @param member
     * @return
     */
    public Long zrevrank(String key,String member){
        return redisConfig.getJedis().zrevrank(key,member);
    }

    /**
     * 返回有序集 key 中，指定区间内的成员 其中成员的位置按 score 值递减(从大到小)来排列
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> ZREVRANGE(String key,long start,long end){
        return redisConfig.getJedis().zrevrange(key,start,end);
    }

}
