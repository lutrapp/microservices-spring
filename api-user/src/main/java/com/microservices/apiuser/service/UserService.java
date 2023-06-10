package com.microservices.apiuser.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.microservices.apiuser.dto.UserDto;
import com.microservices.apiuser.model.User;
import com.microservices.apiuser.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	
	public List<UserDto> getAll(){
		List<User> users = userRepository.findAll();
		return users.stream().map(UserDto::convert)
				.collect(Collectors.toList());
	}
	
	public UserDto findById(long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
		return UserDto.convert(user);
	}
	
	public UserDto save(UserDto userDto) {
		userDto.setDataCadastro(LocalDateTime.now());
		User user = userRepository.save(User.convert(userDto));
		return UserDto.convert(user);
	}
	
	public UserDto delete(long userId) {
		User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException());
		userRepository.delete(user);
		return UserDto.convert(user);
	}
	
	public UserDto findByCpf(String cpf) {
		User user = userRepository.findByCpf(cpf);
		if(user != null) {
			return UserDto.convert(user);
		}
		return null;
	}
	
	public List<UserDto> queryByNome(String nome){
		List<User> users = userRepository.queryByNomeLike(nome);
		return users.stream().map(UserDto::convert).collect(Collectors.toList());		
	}
	
	public UserDto editUser(Long userId, UserDto userDto) {
		User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException());
		if(userDto.getEmail().equals(userDto.getEmail())) {
			user.setEmail(userDto.getEmail());
		}
		if(userDto.getTelefone().equals(userDto.getTelefone())) {
			user.setTelefone(userDto.getTelefone());
		}
		if(userDto.getEndereco().equals(userDto.getEndereco())) {
			user.setEndereco(userDto.getEndereco());
		}
		user = userRepository.save(user);
		return UserDto.convert(user);
	
	}
	
	//com paginação
	public Page<UserDto> getAllPage(Pageable page){
		Page<User> users = userRepository.findAll(page);
		return users.map(UserDto::convert);
	}
}
