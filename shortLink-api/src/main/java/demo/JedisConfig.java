package demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * @author CR7
 * @create 2024/2/23
 */

@Configuration
public class JedisConfig implements CachingConfigurer {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;




    private static JedisPool jedisPool;

    public JedisPool redisPoolFactory() {
        if (jedisPool == null) {
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            //jedisPoolConfig.setMaxTotal(maxTotal);
            //jedisPoolConfig.setMaxIdle(maxIdle);
            //jedisPoolConfig.setMinIdle(minIdle);
            jedisPool = new JedisPool(jedisPoolConfig, host, port, 60000, null);

        }
        return jedisPool;
    }

}
