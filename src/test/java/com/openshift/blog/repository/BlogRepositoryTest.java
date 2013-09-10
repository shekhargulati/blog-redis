package com.openshift.blog.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.openshift.blog.config.AppConfig;
import com.openshift.blog.config.LocalJedisRedisConfig;
import com.openshift.blog.domain.Blog;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class, LocalJedisRedisConfig.class })
@ActiveProfiles("local")
public class BlogRepositoryTest {

    @Inject
    private BlogRepository blogRepository;

    @After
    public void tearDown() {
        blogRepository.cleanDb();
    }

    @Test
    public void saveBlog() {
        Blog blog = new Blog("Getting Started with OpenShift", "Getting Started with OpenShift");

        String blogId = blogRepository.save(blog);

        assertNotNull(blogId);

        Blog persistedBlog = blogRepository.get(blogId);

        assertNotNull(persistedBlog);

        assertEquals(blog, persistedBlog);

        blogRepository.delete(blogId);

        persistedBlog = blogRepository.get(blogId);

        assertNull(persistedBlog);

    }

    @Test
    public void testLatestBlogs(){
        for(int i = 0; i<100;i++){
            Blog blog = new Blog("Getting Started with OpenShift", "Getting Started with OpenShift");

            String blogId = blogRepository.save(blog);
        }
        
        List<Blog> latestBlogs = blogRepository.latestBlogs();
        
        assertEquals(10, latestBlogs.size());
    }
}
