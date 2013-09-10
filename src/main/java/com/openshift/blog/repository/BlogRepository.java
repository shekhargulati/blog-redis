package com.openshift.blog.repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.openshift.blog.domain.Blog;

@Repository
public class BlogRepository {

    @Inject
    private RedisTemplate<String, Object> redisTemplate;

    public String save(Blog blog) {
        HashOperations<String, String, Blog> opsForHash = redisTemplate.opsForHash();
        opsForHash.put("blogs", blog.getId(), blog);
        
        ListOperations<String, Object> opsForList = redisTemplate.opsForList();
        
        
        opsForList.leftPush("latestBlogs", blog);
        opsForList.trim("latestBlogs", 0, 9);
        return blog.getId();

    }

    public Blog get(String id) {
        return (Blog) redisTemplate.opsForHash().get("blogs", id);
    }

    public void delete(String id) {
        redisTemplate.opsForHash().delete("blogs", id);
    }
    
    public List<Blog> latestBlogs(){
        List<Object> objs = redisTemplate.opsForList().range("latestBlogs", 0, -1);
        List<Blog> blogs = new ArrayList<>();
        
        for (Object blog : objs) {
            blogs.add((Blog)blog);
        }
        return blogs;
    }
    
    public void cleanDb(){
        redisTemplate.getConnectionFactory().getConnection().flushDb();
    }
}

