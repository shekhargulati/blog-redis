package com.openshift.blog.controllers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.openshift.blog.domain.Blog;
import com.openshift.blog.repository.BlogRepository;

@Controller
@RequestMapping("/")
public class IndexController {

    @Inject
    private BlogRepository blogRepository;
    
    @RequestMapping
    public ModelAndView index(){
        List<Blog> blogs = blogRepository.latestBlogs();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("blogs", blogs);
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
