package com.microservices.apiuser.model;

import java.time.LocalDateTime;

import com.microservices.apiuser.dto.UserDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserModel{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private String cpf;
	private String endereco;
	private String email;
	private String telefone;
	private LocalDateTime dataCadastro;

	public static UserModel convert(UserDto userDto) {
		UserModel userModel = new UserModel();
		userModel.setNome(userDto.getNome());
		userModel.setEndereco(userDto.getEndereco());
		userModel.setCpf(userDto.getCpf());
		userModel.setEmail(userDto.getEmail());
		userModel.setTelefone(userDto.getTelefone());
		userModel.setDataCadastro(userDto.getDataCadastro());
		return userModel;
	}
}
