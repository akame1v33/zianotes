package com.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDateTime;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) // class level
public @interface Defaultable {
	
	
	public enum Default {
		ON, OFF
	}
	
	Default def() default Default.ON;
	
	String lastModified();
	
}
