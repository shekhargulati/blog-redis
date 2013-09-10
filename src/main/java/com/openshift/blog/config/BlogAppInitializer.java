package com.openshift.blog.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class BlogAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
        webApplicationContext.register(WebappConfig.class, AppConfig.class);

        webApplicationContext.getEnvironment().setActiveProfiles("openshift");

        Dynamic dynamic = servletContext.addServlet("blogApp", new DispatcherServlet(webApplicationContext));
        dynamic.setLoadOnStartup(1);
        dynamic.addMapping("/");

    }

}
