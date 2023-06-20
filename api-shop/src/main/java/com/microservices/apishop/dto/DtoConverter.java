package com.microservices.apishop.dto;

import java.util.stream.Collectors;

import com.microservices.apishop.model.Item;
import com.microservices.apishop.model.Shop;
import com.microservices.shoppingclient.dto.ItemDto;
import com.microservices.shoppingclient.dto.ShopDto;

public class DtoConverter {
	
	public static ItemDto convert(Item item) {
		ItemDto itemDto = new ItemDto();
		itemDto.setProductIdentifier(item.getProductIdentifier());
		itemDto.setPrice(item.getPrice());
		return itemDto;
	}

	public static ShopDto convert(Shop shop) {
		ShopDto shopDto = new ShopDto();
		shopDto.setUserIdentifier(shop.getUserIdentifier());
		shopDto.setTotal(shop.getTotal());
		shopDto.setDate(shop.getDate());
//		shopDto.setItems(shop.getItems().stream().map(ItemDto::convert).collect(Collectors.toList()));
		shopDto.setItems(shop.getItems().stream().map(DtoConverter::convert).collect(Collectors.toList()));
		return shopDto;
	}
	
}
