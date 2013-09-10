package com.openshift.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration
@Profile("openshift")
public class OpenShiftJedisRedisConfig implements RedisConfig {

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(System.getenv("OPENSHIFT_REDIS_HOST"));
        jedisConnectionFactory.setPort(Integer.valueOf(System.getenv("OPENSHIFT_REDIS_PORT")));
        jedisConnectionFactory.setPassword(System.getenv("REDIS_PASSWORD"));
        jedisConnectionFactory.setUsePool(true);
        return jedisConnectionFactory;
    }

}
