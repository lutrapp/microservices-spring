package com.microservices.apiuser.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.apiuser.dto.UserDto;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;

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
	public List<UserDto> getUsers() {
		return usuarios;
	}

	@GetMapping("/{cpf}")
	public UserDto getUserByCpf(@PathVariable String cpf) {
		return usuarios.stream().filter(userDto -> userDto.getCpf().equals(cpf)).findFirst()
				.orElseThrow(() -> new RuntimeException("User not found"));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserDto insertNewUser(@RequestBody @Valid UserDto userDto) {
		userDto.setDataCadastro(LocalDateTime.now());
		usuarios.add(userDto);
		return userDto;
	}
	
	@DeleteMapping("/{cpf}")
	public boolean removeUser(@PathVariable String cpf) {
		return usuarios.removeIf(userDto -> userDto.getCpf().equals(cpf));
	}
}