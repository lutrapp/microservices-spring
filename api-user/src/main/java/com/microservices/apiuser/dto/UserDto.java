package com.microservices.apiuser.dto;

import java.time.LocalDateTime;

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
}
