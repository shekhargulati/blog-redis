package com.openshift.blog.config;

import org.springframework.data.redis.connection.RedisConnectionFactory;

public interface RedisConfig {

    public RedisConnectionFactory redisConnectionFactory();
}
