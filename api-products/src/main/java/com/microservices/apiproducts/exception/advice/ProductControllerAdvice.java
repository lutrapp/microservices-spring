package com.microservices.apiproducts.exception.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.microservices.shoppingclient.dto.ErrorDto;
import com.microservices.shoppingclient.exception.CategoryNotFoundException;
import com.microservices.shoppingclient.exception.ProductNotFoundException;

@ControllerAdvice(basePackages = "com.microservices.apiproducts.controller")
public class ProductControllerAdvice {
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
	@ExceptionHandler(CategoryNotFoundException.class)
	public ErrorDto handleCategoryNotFound(CategoryNotFoundException categoryNotFoundException) {
		ErrorDto errorDto = new ErrorDto();
		errorDto.setStatus(HttpStatus.NOT_FOUND.value());
		errorDto.setMessage("Categoria não encontrada.");
		errorDto.setTimestamp(LocalDateTime.now());
		return errorDto;
	}
}
