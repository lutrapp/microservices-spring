package com.microservices.apiuser.exception.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.microservices.shoppingclient.dto.ErrorDto;
import com.microservices.shoppingclient.exception.UserNotFoundException;

@ControllerAdvice(basePackages = "com.microservices.apiuser.controller")
public class UserControllerAdvice {
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(UserNotFoundException.class)
	public ErrorDto handleUserNotFound(UserNotFoundException userNotFoundException) {
		ErrorDto errorDto = new ErrorDto();
		errorDto.setStatus(HttpStatus.NOT_FOUND.value());
		errorDto.setMessage("Usuário não encontrado.");
		errorDto.setTimestamp(LocalDateTime.now());
		return errorDto;
	}
}
