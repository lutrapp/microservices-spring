package com.microservices.apiuser.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.apiuser.dto.UserDto;

import jakarta.annotation.PostConstruct;

//@RestController
//@RequestMapping("/user")
//
//	@GetMapping("/")
//	public String getMensagem() {
//		return "Tudo ok, Lu";
//	}

@RestController
@RequestMapping("/user")
public class UserController {
	public static List<UserDto> usuarios = new ArrayList<UserDto>();

	@PostConstruct
	public void initiateList() {
		UserDto userDto = new UserDto();
		userDto.setNome("Maria");
		userDto.setCpf("12312312312");
		userDto.setEndereco("Rua y");
		userDto.setEmail("maria.com");
		userDto.setTelefone("9999-8888");
		userDto.setDataCadastro(LocalDateTime.now());

		UserDto userDto2 = new UserDto();
		userDto2.setNome("Antonio");
		userDto2.setCpf("78978978978");
		userDto2.setEndereco("Rua xpto");
		userDto2.setEmail("antonio@email.com");
		userDto2.setTelefone("5656-8989");
		userDto2.setDataCadastro(LocalDateTime.now());

		usuarios.add(userDto);
		usuarios.add(userDto2);
	}
	
	@GetMapping
	public	List<UserDto>	getUsers() {
					return	usuarios;
	}
}