package com.starter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
//@PropertySource(“classpath:custom-cas.properties”)
@ConfigurationProperties // no prefix, find root level values.
public class GlobalProperties {

    private int threadPool;
    private String email;
    
    
    

	
	public int getThreadPool() {
		return threadPool;
	}
	public void setThreadPool(int threadPool) {
		this.threadPool = threadPool;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	@Override
	public String toString() {
		return "\nGlobalProperties\n{threadPool=" + threadPool + "},\n{email=" + email + "}";
	}
	public GlobalProperties() {
		super();
	}

	
}