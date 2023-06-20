package com.microservices.apiuser.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.microservices.apiuser.dto.DtoConverter;
import com.microservices.apiuser.model.User;
import com.microservices.apiuser.repository.UserRepository;
import com.microservices.shoppingclient.dto.UserDto;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@InjectMocks
	private UserService userService;

	@Mock
	private UserRepository userRepository;

	
	@Test
	public void getAllTest() {
		List<User> users = new ArrayList<>();
		users.add(getUser(1, "Luci", "12312312312"));
		users.add(getUser(2, "Maria", "99988877766"));
		
		Mockito.when(userRepository.findAll()).thenReturn(users);
		List<UserDto> usersResult = userService.getAll();
		Assertions.assertEquals(2, usersResult.size());
	}
	
	@Test
	public void saveTest() {
		User userTest = getUser(1, "Melina", "55544433322");
		UserDto userDto = DtoConverter.convert(userTest);
		
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(userTest);
		
		UserDto userDtoTest = userService.save(userDto);
		Assertions.assertEquals("Melina", userDtoTest.getNome());
		Assertions.assertEquals("55544433322", userDtoTest.getCpf());
	}

	@Test
	public void editUserTest() {
		User userTest = getUser(1, "Daniela", "33322211100");
		
		Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(userTest));
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(userTest);
		
		UserDto userDto = DtoConverter.convert(userTest);
		userDto.setEndereco("Endereço editado");
		userDto.setEmail("teste@teste.com.br");
		
		UserDto user = userService.editUser(1L, userDto);
		
		Assertions.assertEquals("Endereço editado", user.getEndereco());
		Assertions.assertEquals("teste@teste.com.br", user.getEmail());
		
	}
	
	public static User getUser(Integer id, String nome, String cpf) {
		User user = new User();
		user.setId(id);
		user.setNome(nome);
		user.setCpf(cpf);
		user.setEndereco("endereco");
		user.setTelefone("1234567");
		return user;
	}
}
