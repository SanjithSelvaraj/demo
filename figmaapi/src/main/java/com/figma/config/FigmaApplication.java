package com.figma.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.figma")
@SpringBootApplication
public class FigmaApplication {
	
	
	public static void main(String[] args) { 
        SpringApplication.run(FigmaApplication.class, args); 
    } 
}
