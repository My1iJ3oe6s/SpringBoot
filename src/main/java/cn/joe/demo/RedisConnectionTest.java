package cn.joe.demo;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by myijo on 2018/5/3.
 */
public class RedisConnectionTest {

    public static void main(String[] args) {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName("192.168.232.128");
        factory.setPort(6379);
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.opsForValue().get("msg");
    }
}
