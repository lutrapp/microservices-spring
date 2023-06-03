package com.microservices.apiuser.dto;

import java.time.LocalDateTime;

import com.microservices.apiuser.model.UserModel;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserDto {
	@NotBlank(message = "Nome é obrigatório")
	private	String	nome;
	@NotBlank(message = "cpf é obrigatório")
	private	String	cpf;
	private	String	endereco;
	@NotBlank(message = "Email é obrigatório")
	private	String	email;
	private	String	telefone;
	private	LocalDateTime	dataCadastro;


	public	static	UserDto	convert(UserModel userModel) {
		UserDto	userDto	=	new	UserDto();
		userDto.setNome(userModel.getNome());
		userDto.setEndereco(userModel.getEndereco());
		userDto.setCpf(userModel.getCpf());
		userDto.setEmail(userModel.getEmail());
		userDto.setTelefone(userModel.getTelefone());
		userDto.setDataCadastro(userModel.getDataCadastro());
		return	userDto;
}

}
