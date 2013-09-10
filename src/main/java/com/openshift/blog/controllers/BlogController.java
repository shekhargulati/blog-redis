package com.openshift.blog.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.openshift.blog.domain.Blog;
import com.openshift.blog.repository.BlogRepository;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    @Inject
    private BlogRepository blogRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView blogForm() {
        ModelAndView modelAndView = new ModelAndView("create");
        return modelAndView;
    }
    
    
    @RequestMapping(method = RequestMethod.POST)
    public RedirectView createBlog(Blog blog) {
        String blogId = blogRepository.save(blog);
        return new RedirectView("/blogs/" + blogId, true);
    }
    
    @RequestMapping(value="/{blogId}")
    public ModelAndView showBlog(@PathVariable("blogId") String blogId){
        Blog blog = blogRepository.get(blogId);
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("blog", blog);
        return new ModelAndView("show", model);
    }
}
