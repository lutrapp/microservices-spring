package com.microservices.apishop.model;


import com.microservices.shoppingclient.dto.ItemDto;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Item {
	private String productIdentifier;
	private Float price;

	public static Item convert(ItemDto itemDto) {
		Item item = new Item();
		item.setProductIdentifier(itemDto.getProductIdentifier());
		item.setPrice(itemDto.getPrice());
		return item;
	}
}
