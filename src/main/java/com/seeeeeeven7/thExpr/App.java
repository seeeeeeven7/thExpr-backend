package com.seeeeeeven7.thExpr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan("com.seeeeeeven7.thExpr.controller")

@RestController
@EnableAutoConfiguration
public class App 
{
    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }
}