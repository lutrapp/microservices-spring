package com.microservices.apishop.dto;

import com.microservices.apishop.model.Item;

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
public class ItemDto {
	@NotBlank
	private String productIdentifier;
	@NotNull
	private Float price;

	public static ItemDto convert(Item item) {
		ItemDto itemDto = new ItemDto();
		itemDto.setProductIdentifier(item.getProductIdentifier());
		itemDto.setPrice(item.getPrice());
		return itemDto;
	}
}
