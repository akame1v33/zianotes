package com.starter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.starter.properties.AppProperties;
import com.starter.properties.GlobalProperties;

@SpringBootApplication
public class PropertiesDemoApplication implements CommandLineRunner {
	
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AppProperties appProperties;
	
	@Autowired
	private GlobalProperties globalProperies;
	
	public static void main(String[] args) {
		SpringApplication.run(PropertiesDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		System.out.println("ye");
		
		String app = appProperties.toString();
		String global = globalProperies.toString();
		
//		 logger.debug("Welcome {}, {}", app, global);
		 logger.info( "test" );
	
	}
}
