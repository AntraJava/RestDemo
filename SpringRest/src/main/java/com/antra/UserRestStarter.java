package com.antra;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages= {"com.antra"})
public class UserRestStarter {

	public static void main(String[] args) {
		
		SpringApplication.run(UserRestStarter.class, args);
	}
}
