package com.microservices.apishop.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.microservices.shoppingclient.dto.ErrorDto;
import com.microservices.shoppingclient.dto.UserDto;
import com.microservices.shoppingclient.exception.UserNotFoundException;

import reactor.core.publisher.Mono;

@Service
public class UserService {
	@Value("${USER_API_URL:http://localhost:8081}")
	private String userApiURL;
//	private String userApiURL = "http://localhost:8081";

	public UserDto getUserByCpf(String cpf, String key) {
		try {
			WebClient webClient = WebClient.builder()
					.baseUrl(userApiURL)
					.build();
			
			Mono<UserDto> user = webClient.get()
					.uri("/user/cpf/" + cpf + "?key=" + key)
					.retrieve()
					.bodyToMono(UserDto.class);
			
			return user.block();
		} catch (Exception e) {
			throw new UserNotFoundException();
		}
	}
}
