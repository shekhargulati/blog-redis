package com.openshift.blog.config;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = LocalJedisRedisConfig.class)
@ActiveProfiles("local")
public class LocalJedisRedisConfigTest {

    @Inject
    private RedisConnectionFactory redisConnectionFactory;

    @Test
    public void testRedisConnectionFactory() {
        assertNotNull(redisConnectionFactory);
    }

}
