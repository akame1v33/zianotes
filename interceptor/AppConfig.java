package com.concretepage.config;  
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import com.concretepage.interceptors.LoggingInterceptor;
import com.concretepage.interceptors.TransactionInterceptor;
@Configuration 
@ComponentScan("com.concretepage") 
@EnableWebMvc   
public class AppConfig extends WebMvcConfigurerAdapter  {  
	@Bean  
        public UrlBasedViewResolver setupViewResolver() {  
            UrlBasedViewResolver resolver = new UrlBasedViewResolver();  
            resolver.setPrefix("/views/");  
            resolver.setSuffix(".jsp");  
            resolver.setViewClass(JstlView.class);  
            return resolver;  
        }
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(new LoggingInterceptor());
	    registry.addInterceptor(new TransactionInterceptor()).addPathPatterns("/person/save/*");
	}
} 