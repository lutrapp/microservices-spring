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
public class User{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private String cpf;
	private String endereco;
	private String email;
	private String telefone;
	private LocalDateTime dataCadastro;

	public static User convert(UserDto userDto) {
		User user = new User();
		user.setNome(userDto.getNome());
		user.setEndereco(userDto.getEndereco());
		user.setCpf(userDto.getCpf());
		user.setEmail(userDto.getEmail());
		user.setTelefone(userDto.getTelefone());
		user.setDataCadastro(userDto.getDataCadastro());
		return user;
	}
}
