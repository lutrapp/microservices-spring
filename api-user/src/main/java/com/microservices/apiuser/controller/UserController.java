package com.microservices.apiuser.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.apiuser.service.UserService;
import com.microservices.shoppingclient.dto.UserDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;

	@GetMapping
	public List<UserDto> getUsers() {
		return userService.getAll();
	}
	
	@GetMapping("/{id}")
	public UserDto findById(@PathVariable Long id) {
		return userService.findById(id);
	}

	@GetMapping("/{cpf}/cpf")
	public UserDto findByCpf(@PathVariable String cpf) {
//		return usuarios.stream().filter(userDto -> userDto.getCpf().equals(cpf)).findFirst()
//				.orElseThrow(() -> new RuntimeException("User not found"));
		return userService.findByCpf(cpf);
	}

	@PostMapping("/insertnewuser")
	@ResponseStatus(HttpStatus.CREATED)
	public UserDto insertNewUser(@RequestBody @Valid UserDto userDto) {
		return userService.save(userDto);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void deleteUser(@PathVariable Long id) throws UserNotFound Exception  {
	public void deleteUser(@PathVariable Long id)  { //TODO criar classe de exceções
//		return usuarios.removeIf(userDto -> userDto.getCpf().equals(cpf));
		userService.delete(id);
	}
	
	@GetMapping("/searchByName")
	public List<UserDto> searchByName(@RequestParam(name="nome", required = true) String nome){
		return userService.queryByNome(nome);
	}
	
	@GetMapping("/searchByNameContaining")
	public List<UserDto> searchByNameContaining(@RequestParam(name="nome", required = true) String nome){
		return userService.queryByNomeContaining(nome);
	}
	
	
	@PatchMapping("/{id}")
	public UserDto editUser(@PathVariable Long id, @RequestBody UserDto userDto) {
		return userService.editUser(id, userDto);
	}
	
	@GetMapping("/pageable")
	public	Page<UserDto> getUsersPage(Pageable	pageable) {
		return	userService.getAllPage(pageable);
	}
}