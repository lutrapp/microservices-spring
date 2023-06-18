package com.microservices.apishop.exception.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.microservices.shoppingclient.dto.ErrorDto;
import com.microservices.shoppingclient.exception.ProductNotFoundException;
import com.microservices.shoppingclient.exception.UserNotFoundException;

@ControllerAdvice(basePackages = "com.microservices.apishop.controller")
public class ShopControllerAdvice {
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ProductNotFoundException.class)
	public ErrorDto handleUserNotFound(ProductNotFoundException userNotFoundException) {
		ErrorDto errorDto = new ErrorDto();
		errorDto.setStatus(HttpStatus.NOT_FOUND.value());
		errorDto.setMessage("Produto não encontrado.");
		errorDto.setTimestamp(LocalDateTime.now());
		return errorDto;
	}

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
