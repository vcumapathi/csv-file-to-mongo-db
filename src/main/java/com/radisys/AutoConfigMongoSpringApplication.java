package com.radisys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class AutoConfigMongoSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoConfigMongoSpringApplication.class, args);
	}

}
