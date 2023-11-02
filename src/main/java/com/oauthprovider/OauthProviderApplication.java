package com.oauthprovider;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/* 
 * @author Chiranjeevi
*/

@SpringBootApplication
public class OauthProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OauthProviderApplication.class, args);
	}

	@Bean
	ModelMapper mapper() {
		return new ModelMapper();
	}

}
