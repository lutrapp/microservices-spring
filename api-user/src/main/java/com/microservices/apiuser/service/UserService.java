package com.microservices.apiuser.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.microservices.apiuser.dto.UserDto;
import com.microservices.apiuser.model.UserModel;
import com.microservices.apiuser.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	
	public List<UserDto> getAll(){
		List<UserModel> users = userRepository.findAll();
		return users.stream().map(UserDto::convert)
				.collect(Collectors.toList());
	}
	
	public UserDto findById(long userId) {
		UserModel userModel = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
		return UserDto.convert(userModel);
	}
	
	public UserDto save(UserDto userDto) {
		userDto.setDataCadastro(LocalDateTime.now());
		UserModel userModel = userRepository.save(UserModel.convert(userDto));
		return UserDto.convert(userModel);
	}
	
	public UserDto delete(long userId) {
		UserModel userModel = userRepository.findById(userId).orElseThrow(()-> new RuntimeException());
		userRepository.delete(userModel);
		return UserDto.convert(userModel);
	}
	
	public UserDto findByCpf(String cpf) {
		UserModel userModel = userRepository.findByCpf(cpf);
		if(userModel != null) {
			return UserDto.convert(userModel);
		}
		return null;
	}
	
	public List<UserDto> queryByName(String name){
		List<UserModel> users = userRepository.queryByNameLike(name);
		return users.stream().map(UserDto::convert).collect(Collectors.toList());		
	}
	
	public UserDto editUser(Long userId, UserDto userDto) {
		UserModel userModel = userRepository.findById(userId).orElseThrow(()-> new RuntimeException());
		if(userDto.getEmail().equals(userDto.getEmail())) {
			userModel.setEmail(userDto.getEmail());
		}
		if(userDto.getTelefone().equals(userDto.getTelefone())) {
			userModel.setTelefone(userDto.getTelefone());
		}
		if(userDto.getEndereco().equals(userDto.getEndereco())) {
			userModel.setEndereco(userDto.getEndereco());
		}
		userModel = userRepository.save(userModel);
		return UserDto.convert(userModel);
	
	}
	
	//com paginação
	public Page<UserDto> getAllPage(Pageable page){
		Page<UserModel> users = userRepository.findAll(page);
		return users.map(UserDto::convert);
	}
}
