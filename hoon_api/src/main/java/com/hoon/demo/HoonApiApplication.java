package com.hoon.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan( "com.hoon" )
public class HoonApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HoonApiApplication.class, args);
	}

}
