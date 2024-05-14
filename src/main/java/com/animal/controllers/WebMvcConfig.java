package com.animal.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//this configuration is used for showing uploaded image at the time of getting animal
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	    
	    @Value("${file.upload-dir}")
	    private String uploadDir;

	    @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/uploads/**")
	                .addResourceLocations("file:" + uploadDir + "/");
	    }
}
