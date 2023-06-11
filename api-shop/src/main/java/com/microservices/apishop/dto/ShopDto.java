package com.microservices.apishop.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.microservices.apishop.model.Shop;

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
	@NotBlank
	private String userIdentifier;

	private Float total;
	@NotNull
	private LocalDateTime date;
	@NotNull
	private List<ItemDto> items;

	public static ShopDto convert(Shop shop) {
		ShopDto shopDto = new ShopDto();
		shopDto.setUserIdentifier(shop.getUserIdentifier());
		shopDto.setTotal(shop.getTotal());
		shopDto.setDate(shop.getDate());
		shopDto.setItems(shop.getItems().stream().map(ItemDto::convert).collect(Collectors.toList()));
		return shopDto;
	}
}