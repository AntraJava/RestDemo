package com.antra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= {"com.antra"})
public class SpringBootStarter {

	public static void main(String[] args) {
		
		SpringApplication.run(SpringBootStarter.class, args);
	}
}
