package com.cmc.demo.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * Redis Demo
 * @author Thomas Lee
 * @version 2017年4月5日 下午1:50:36
 */
public class RedisDemo {

    private static final Logger LOG = LoggerFactory.getLogger(RedisDemo.class);

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    /**
     * 直接通过Jedis进行缓存测试 
     * @author Thomas Lee
     * @version 2017年3月12日 下午12:36:37
     */
    public void testRedis() {
        try (Jedis jedis = new Jedis("192.168.98.128", 6379)) {
            String key = "name";
            String value = "lcb";
            jedis.set(key, value);
            LOG.info(jedis.get(key));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * 通过Spring和Redis集成
     * @author Thomas Lee
     * @version 2017年3月12日 下午12:35:52
     */
    public void testSpringRedis() {
        ShardedJedis jedis = shardedJedisPool.getResource();
        String key = "name";
        String value = "lcb";
        jedis.set(key, value);
        LOG.info(jedis.get(key));
    }

}