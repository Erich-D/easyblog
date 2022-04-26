/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erich.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author etdeh
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    public final static String IMAGE_RESOURCE_BASE = "/images/";
	public final static String IMAGE_FILE_BASE = "/engage/images/";	
	public final static String BASE_URL = "http://localhost:8080";
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(IMAGE_RESOURCE_BASE + "**")
                .addResourceLocations("file:" + IMAGE_FILE_BASE);
    }
}
