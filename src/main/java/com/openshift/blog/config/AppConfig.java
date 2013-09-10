package com.openshift.blog.config;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.openshift.blog.domain.Blog;
import com.openshift.blog.repository.BlogRepository;

@Configuration
@ComponentScan(basePackageClasses = { AppConfig.class, BlogRepository.class }, excludeFilters = { @Filter(EnableWebMvc.class) })
public class AppConfig {

    @Inject
    private RedisConfig redisConfig;

    @Bean
    public RedisTemplate<String, Blog> redisTemplate() {
        RedisTemplate<String, Blog> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConfig.redisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new JacksonJsonRedisSerializer<>(Blog.class));
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new JacksonJsonRedisSerializer<>(Blog.class));
        return template;
    }
}
