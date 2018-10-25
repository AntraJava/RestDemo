package com.antra.SpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;

//@EnableAutoConfiguration(exclude = { DataSourceTransactionManagerAutoConfiguration.class,WebMvcAutoConfiguration.class })
@SpringBootApplication(scanBasePackages= {"com.antra"})
public class SpringBootStarter {

	public static void main(String[] args) {
		
		SpringApplication.run(SpringBootStarter.class, args);
	}
}
