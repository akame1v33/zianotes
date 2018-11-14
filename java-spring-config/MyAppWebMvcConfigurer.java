package com.example.demo;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;


@Configuration
public class MyAppWebMvcConfigurer implements WebMvcConfigurer {
	
	 private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
	            "classpath:/META-INF/resources/", "classpath:/resources/",
	            "classpath:/static/", "classpath:/public/","classpath:/images"};
	 
	 
	 
	 private final String[] test = {
			 "classpath:/images/", 
	 };
	 
		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry.addResourceHandler("/images/**") // this is the url proxy
			.addResourceLocations(test); // THIS IS THE ACTUAL SHIT
			

		    registry.addResourceHandler("/**")//will look up
		    .addResourceLocations("classpath:/static/")///and this is the proxy
		    .resourceChain(true)
		    .addResolver(new PathResourceResolver() {
		        @Override
		        protected Resource getResource(String resourcePath, Resource location) throws IOException {
		        	
		            Resource requestedResource = location.createRelative(resourcePath);
		      
		            return requestedResource.exists() && requestedResource.isReadable() ? requestedResource : new ClassPathResource("/static/index.html");
		        }
		    });
		}
}