package com.seeeeeeven7.thExpr;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.seeeeeeven7.thExpr.filter.HTTPBasicAuthorizeFilter;

@ComponentScan("com.seeeeeeven7.thExpr")

@SpringBootApplication
public class App 
{
    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }
    
    @Autowired HTTPBasicAuthorizeFilter httpBasicAuthorizeFilter;
    
    @Bean
    public HTTPBasicAuthorizeFilter get() {
    	return new HTTPBasicAuthorizeFilter();
    }
    
    @Bean  
    public FilterRegistrationBean  filterRegistrationBean() {  
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(); 
        registrationBean.setFilter(httpBasicAuthorizeFilter);  
        List<String> urlPatterns = new ArrayList<String>();  
        urlPatterns.add("/api/*");  
        registrationBean.setUrlPatterns(urlPatterns);  
        return registrationBean;  
    } 
}