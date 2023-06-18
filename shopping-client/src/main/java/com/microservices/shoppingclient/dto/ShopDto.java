package com.microservices.shoppingclient.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShopDto {

	private String userIdentifier; //cpf

	private Float total;
	
	private LocalDateTime date;

	private List<ItemDto> items;

}