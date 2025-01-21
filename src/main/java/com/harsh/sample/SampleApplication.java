package com.harsh.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SampleApplication {

 	public static void main(String[] args) {
		SpringApplication.run(SampleApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(){
		 return new RestTemplate();
	}

}

// PlatformTransactionManager interface
// MongoTransactionManager sentimentalisation


  













