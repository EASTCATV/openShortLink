package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Component
public class RedisUtils {

    private static JedisPool jedisPool;

    @Autowired
    private JedisConfig redisConfig;

    //private int DEFAULT_INDEX=0;
    private int DEFAULT_INDEX=1;

    /**
     * JedisPool 无法通过@Autowired注入，可能由于是方法bean的原因，此处可以先注入RedisConfig，
     * 然后通过@PostConstruct初始化的时候将factory直接赋给jedisPool
     */
    @PostConstruct
    public void init() {
        if (null == jedisPool) {
            jedisPool = redisConfig.redisPoolFactory();
        }
    }


    private void returnResource(Jedis jedis){
        if(jedis!=null) {
            jedis.close();
        }
    }

    private synchronized Jedis getResource(){
        return jedisPool.getResource();
    }

    public String get(Integer dbIndex,String key){
        Jedis jedis = null;
        try {
            jedis = getResource();
            if(dbIndex==null) {
                dbIndex=DEFAULT_INDEX;
            }
            jedis.select(dbIndex);
            return jedis.get(key);
        } finally {
            returnResource(jedis);
        }
    }
    public String get(String key){
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.select(DEFAULT_INDEX);
            return jedis.get(key);
        } finally {
            returnResource(jedis);
        }
    }

    public Set<String> keys(Integer dbIndex, String key){
        Jedis jedis = null;
        try {
            jedis = getResource();
            if(dbIndex==null) {
                dbIndex=DEFAULT_INDEX;
            }
            jedis.select(dbIndex);
            return jedis.keys(key);
        } finally {
            returnResource(jedis);
        }
    }

    public void del(Integer dbIndex,String key){
        Jedis jedis = null;
        try {
            jedis = getResource();
            if(dbIndex==null) {
                dbIndex=DEFAULT_INDEX;
            }
            jedis.select(dbIndex);
            jedis.del(key);
        } finally {
            returnResource(jedis);
        }
    }
    public void del(String... key){
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.select(DEFAULT_INDEX);
            jedis.del(key);
        } finally {
            returnResource(jedis);
        }
    }

    public void delDbBykeys(Integer index,String [] keys){
        Jedis jedis = null;
        try {
            jedis = getResource();
            if(index==null) {
                index=DEFAULT_INDEX;
            }
            jedis.select(index);
            jedis.del(keys);
        } finally {
            returnResource(jedis);
        }
    }

    public void set(Integer dbIndex,String key,String value){
        Jedis jedis = null;
        try {
            jedis = getResource();
            if(dbIndex==null) {
                dbIndex=DEFAULT_INDEX;
            }
            jedis.select(dbIndex);
            jedis.set(key,value);
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 普通缓存放入并设置时间
     * @param key 键
     * @param value 值
     * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean set(String key,String value,long time){
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.select(DEFAULT_INDEX);
            jedis.setex(key,time,value);
        } finally {
            returnResource(jedis);
        }
        return true;
    }
    public boolean set(String key,String value){
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.select(DEFAULT_INDEX);
            jedis.set(key,value);
        } finally {
            returnResource(jedis);
        }
        return true;
    }

    public void setEx(Integer dbIndex,String key,int seconds,String value){
        Jedis jedis = null;
        try {
            jedis = getResource();
            if(dbIndex==null) {
                dbIndex=DEFAULT_INDEX;
            }
            jedis.select(dbIndex);
            jedis.setex(key,seconds,value);
        } finally {
            returnResource(jedis);
        }
    }

    public void hset(Integer dbIndex,String key,String filed,String value){
        Jedis jedis = null;
        try {
            jedis = getResource();
            if(dbIndex==null) {
                dbIndex=DEFAULT_INDEX;
            }
            jedis.select(dbIndex);
            jedis.hset(key,filed,value);
        } finally {
            returnResource(jedis);
        }
    }
    public boolean hset(Integer dbIndex,String key,String filed,String value,long time){
        Jedis jedis = null;
        try {
            jedis = getResource();
            if(dbIndex==null) {
                dbIndex=DEFAULT_INDEX;
            }
            jedis.select(dbIndex);
            jedis.hset(key,filed,value);
        } finally {
            returnResource(jedis);
        }
        return true;
    }

    public boolean hset(String key,String filed,String value){
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.select(DEFAULT_INDEX);
            jedis.hset(key,filed,value);
        } finally {
            returnResource(jedis);
        }
        return true;
    }

    public void hset(Integer dbIndex,String key,String filed,String value,int seconds){
        Jedis jedis = null;
        try {
            jedis = getResource();
            if(dbIndex==null) {
                dbIndex=DEFAULT_INDEX;
            }
            jedis.select(dbIndex);
            jedis.hset(key,filed,value);
            jedis.expire(key,seconds);
        } finally {
            returnResource(jedis);
        }
    }

    public void hdel(Integer dbIndex,String key,String filed){
        Jedis jedis = null;
        try {
            jedis = getResource();
            if(dbIndex==null) {
                dbIndex=DEFAULT_INDEX;
            }
            jedis.select(dbIndex);
            jedis.hdel(key,filed);
        } finally {
            returnResource(jedis);
        }
    }

    public String hget(Integer dbIndex,String key,String filed){
        Jedis jedis = null;
        try {
            jedis = getResource();
            if(dbIndex==null) {
                dbIndex=DEFAULT_INDEX;
            }
            jedis.select(dbIndex);
            return jedis.hget(key,filed);
        } finally {
            returnResource(jedis);
        }
    }

    public Map<String,String> hgetAll(Integer dbIndex, String key){
        Jedis jedis = null;
        try {
            jedis = getResource();
            if(dbIndex==null) {
                dbIndex=DEFAULT_INDEX;
            }
            jedis.select(dbIndex);
            return jedis.hgetAll(key);
        } finally {
            returnResource(jedis);
        }
    }

    public Long sAdd(Integer dbIndex,String key,String value){
        Jedis jedis = null;
        try {
            jedis = getResource();
            if(dbIndex==null) {
                dbIndex=DEFAULT_INDEX;
            }
            jedis.select(dbIndex);
            return jedis.sadd(key,value);
        } finally {
            returnResource(jedis);
        }
    }

    public Long scard(Integer dbIndex,String key){
        Jedis jedis=null;
        try {
            jedis=getResource();
            if(dbIndex==null){
                dbIndex=DEFAULT_INDEX;
            }
            jedis.select(dbIndex);
            return jedis.scard(key);
        } finally {
            returnResource(jedis);
        }
    }

    public boolean sismember(Integer dbIndex,String key,String value){
        Jedis jedis = null;
        try {
            jedis = getResource();
            if(dbIndex==null) {
                dbIndex=DEFAULT_INDEX;
            }
            jedis.select(dbIndex);
            return jedis.sismember(key,value);
        } finally {
            returnResource(jedis);
        }
    }

    public Long srem(Integer dbIndex,String key,String value){
        Jedis jedis = null;
        try {
            jedis = getResource();
            if(dbIndex==null) {
                dbIndex=DEFAULT_INDEX;
            }
            jedis.select(dbIndex);
            return jedis.srem(key,value);
        } finally {
            returnResource(jedis);
        }
    }


    public Set<String> smembers(Integer dbIndex,String key){
        Jedis jedis = null;
        try {
            jedis = getResource();
            if(dbIndex==null) {
                dbIndex=DEFAULT_INDEX;
            }
            jedis.select(dbIndex);
            return jedis.smembers(key);
        } finally {
            returnResource(jedis);
        }
    }

    public Long zcount(Integer dbIndex,String key,Integer min,Integer max){
        Jedis jedis=null;
        try {
            jedis = getResource();
            if (dbIndex == null) {
                dbIndex = DEFAULT_INDEX;
            }
            jedis.select(dbIndex);
            return jedis.zcount(key, min, max);
        }finally {
            returnResource(jedis);
        }
    }

    public Long zcard(Integer dbIndex,String key){
        Jedis jedis = null;
        try {
            jedis = getResource();
            if(dbIndex==null) {
                dbIndex=DEFAULT_INDEX;
            }
            jedis.select(dbIndex);
            return jedis.zcard(key);
        } finally {
            returnResource(jedis);
        }
    }

    public void setZSetEx(Integer dbIndex,String key,Set<String> set,int seconds){
        Jedis jedis = getResource();
        try {
            if(dbIndex==null) {
                dbIndex=DEFAULT_INDEX;
            }
            jedis.select(dbIndex);
            int i=0;
            for(String value:set){
                jedis.zadd(key,i,value);
                i++;
            }
            jedis.expire(key,seconds);
        } finally {
            returnResource(jedis);
        }
    }

    public Boolean exists(Integer dbIndex, String key) {
        Jedis jedis = getResource();
        try {
            if (dbIndex == null) {
                dbIndex = DEFAULT_INDEX;
            }
            jedis.select(dbIndex);
            return jedis.exists(key);
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        } finally {
            returnResource(jedis);
        }
    }

    public List<String> lGet(String tokenKey, int start, long end) {
        Jedis jedis = getResource();
        try {
            jedis.select(DEFAULT_INDEX);
            return jedis.lrange(tokenKey, start, end);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        } finally {
            returnResource(jedis);
        }
    }
}
