package com.yunji.dango.redis;

import com.yunji.dango.shiro.uti.SerializeUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.concurrent.Callable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisCache implements Cache{
    private RedisTemplate<String, Object> redisTemplate;
    private String name;
    private long expireTime;
    private Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    public void evict(Object key){
        this.logger.info("--------删除缓存-------" + key.toString());
        final String keyf = key.toString();
        System.out.println(keyf);
        this.redisTemplate.execute(new RedisCallback(){
            public Long doInRedis(RedisConnection connection)
                    throws DataAccessException
            {
                return connection.del(new byte[][] { keyf.getBytes() });
            }
        });
    }

    public void clear(){
        this.logger.info("-----缓存清理-----");
        this.redisTemplate.execute(new RedisCallback(){
            public String doInRedis(RedisConnection connection)
                    throws DataAccessException{
                connection.flushDb();
                return "ok";
            }
        });
    }

    public Cache.ValueWrapper get(Object key){
        this.logger.info("------缓存获取-------" + key.toString());
        final String keyf = key.toString();

        Object object = this.redisTemplate.execute(new RedisCallback(){
            public Object doInRedis(RedisConnection connection)
                    throws DataAccessException{
                byte[] key = keyf.getBytes();
                byte[] value = connection.get(key);
                if (value == null)
                {
                    RedisCache.this.logger.info("------缓存不存在-------");
                    return null;
                }
                return SerializeUtils.deserialize(value);
            }
        });
        Cache.ValueWrapper obj = object != null ? new SimpleValueWrapper(object) : null;
        this.logger.info("------获取到内容-------" + obj);
        return obj;
    }

    public void put(Object key, Object value){
        this.logger.info("-------加入缓存------ key---:" + key + " value---:" + value);
        final String keyString = key.toString();
        final Object valuef = value;
        this.redisTemplate.execute(new RedisCallback(){
            public Long doInRedis(RedisConnection connection)
                    throws DataAccessException
            {
                byte[] keyb = keyString.getBytes();
                byte[] valueb = SerializeUtils.serialize((Serializable)valuef);
                connection.set(keyb, valueb);
                if (RedisCache.this.expireTime > 0L) {
                    connection.expire(keyb, RedisCache.this.expireTime);
                }
                return Long.valueOf(1L);
            }
        });
    }

    public void putObject(Object key, Object value, final long time){
        this.logger.info("-------加入缓存------ key---:" + key + " value---:" + value);
        final String keyString = key.toString();
        final Object valuef = value;
        this.redisTemplate.execute(new RedisCallback(){
            public Long doInRedis(RedisConnection connection)
                    throws DataAccessException
            {
                byte[] keyb = keyString.getBytes();
                byte[] valueb = null;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                try{
                    ObjectOutputStream oos = new ObjectOutputStream(bos);
                    oos.writeObject(valuef);
                    oos.flush();
                    valueb = bos.toByteArray();
                    oos.close();
                    bos.close();
                }catch (Exception ex){
                    RedisCache.this.logger.info("------缓存增加异常------ key:" + keyString + " value:" + valuef);
                    RedisCache.this.logger.error(ex.getMessage());
                    ex.printStackTrace();
                }
                connection.set(keyb, valueb);
                if (time > 0L) {
                    connection.expire(keyb, time);
                } else if (RedisCache.this.expireTime > 0L) {
                    connection.expire(keyb, RedisCache.this.expireTime);
                }
                return Long.valueOf(1L);
            }
        });
    }

    public Object getObject(Object key){
        this.logger.info("------获取缓存-------" + key.toString());
        final String keyf = key.toString();
        Object object = this.redisTemplate.execute(new RedisCallback(){
            public Object doInRedis(RedisConnection connection)
                    throws DataAccessException
            {
                byte[] key = keyf.getBytes();
                byte[] value = connection.get(key);
                if (value == null){
                    RedisCache.this.logger.info("------缓存不存在-------");
                    return null;
                }
                Object obj = null;
                try{
                    ByteArrayInputStream bis = new ByteArrayInputStream(value);
                    ObjectInputStream ois = new ObjectInputStream(bis);
                    obj = ois.readObject();
                    ois.close();
                    bis.close();
                }catch (Exception ex){
                    RedisCache.this.logger.info("------获取异常-------");
                    RedisCache.this.logger.error(ex.getMessage());
                    ex.printStackTrace();
                }
                return obj;
            }
        });
        this.logger.info("------获取到-------" + object);
        return object;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Object getNativeCache(){
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    public long getExpireTime(){
        return expireTime;
    }

    public void setExpireTime(long expireTime){
        this.expireTime = expireTime;
    }

    public <T> T get(Object o, Callable<T> callable){
        return null;
    }

    public <T> T get(Object key, Class<T> aClass){
        return null;
    }

    public Cache.ValueWrapper putIfAbsent(Object o, Object o1){
        return null;
    }
}
