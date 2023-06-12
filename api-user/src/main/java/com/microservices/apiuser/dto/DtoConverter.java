package com.microservices.apiuser.dto;

import com.microservices.apiuser.model.User;
import com.microservices.shoppingclient.dto.UserDto;

public class DtoConverter {

	public static UserDto convert(User user) {
		UserDto userDto = new UserDto();
		userDto.setNome(user.getNome());
		userDto.setEndereco(user.getEndereco());
		userDto.setCpf(user.getCpf());
		userDto.setEmail(user.getEmail());
		userDto.setTelefone(user.getTelefone());
		userDto.setDataCadastro(user.getDataCadastro());
		return userDto;
	}

}
