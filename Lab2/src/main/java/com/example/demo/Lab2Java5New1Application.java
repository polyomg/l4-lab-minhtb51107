package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.demo", "poly.edu"})
public class Lab2Java5New1Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab2Java5New1Application.class, args);
	}

}
