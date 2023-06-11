package com.microservices.apishop.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.microservices.apishop.dto.ShopDto;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="shop")
public class Shop {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String userIdentifier;
	private float total;
	private LocalDateTime date;
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "item", joinColumns = @JoinColumn(name = "shop_id"))
	private List<Item> items;

	public static Shop convert(ShopDto shopDto) {
		Shop shop = new Shop();
		shop.setUserIdentifier(shopDto.getUserIdentifier());
		shop.setTotal(shopDto.getTotal());
		shop.setDate(shopDto.getDate());
		shop.setItems(shopDto.getItems().stream().map(Item::convert).collect(Collectors.toList()));
		return shop;
	}
}