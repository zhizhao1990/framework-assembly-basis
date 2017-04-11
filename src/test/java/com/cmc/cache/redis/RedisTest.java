package com.cmc.cache.redis;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

// @RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration({ "classpath:config/spring-context.xml" })
public class RedisTest {

    private static final Logger LOG = LoggerFactory.getLogger(RedisTest.class);

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    /**
     * 直接通过Jedis进行缓存测试 
     * @author Thomas Lee
     * @version 2017年3月12日 下午12:36:37
     */
    @Test
    public void testRedis() {
        Jedis jedis = null;
        try {
            jedis = new Jedis("192.168.98.128", 6379);
            String key = "name";
            String value = "lcb";
            jedis.set(key, value);
            LOG.info(jedis.get(key));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    /**
     * 通过@ContextConfiguration进行Spring和Redis集成测试
     * @author Thomas Lee
     * @version 2017年3月12日 下午12:35:52
     */
    @Test
    public void testSpringRedis() {
        // 通过ClassPathXmlApplicationContext加载Spring容器
        // ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/config/spring-context.xml");
        // context.start();

        // ShardedJedisPool shardedJedisPool = (ShardedJedisPool) context.getBean("shardedJedisPool");
        ShardedJedis jedis = shardedJedisPool.getResource();
        String key = "name";
        String value = "lcb";
        jedis.set(key, value);
        LOG.info(jedis.get(key));
        // context.stop();
        // context.close();
    }

}