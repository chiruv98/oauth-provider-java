package com.oauthprovider;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.oauthprovider.exception.ErrorResponse;

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

	@Bean
	ErrorResponse errorResponse() {
		return new ErrorResponse();
	}

}
