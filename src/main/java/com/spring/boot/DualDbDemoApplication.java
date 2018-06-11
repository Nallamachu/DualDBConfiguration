package com.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.spring.boot.h2.config," 
		+ "com.spring.boot.h2.controller,"
		+ "com.spring.boot.h2.repository," 
		+ "com.spring.boot.inf.config," 
		+ "com.spring.boot.inf.controller,"
		+ "com.spring.boot.inf.repository")
public class DualDbDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DualDbDemoApplication.class, args);
	}
}
