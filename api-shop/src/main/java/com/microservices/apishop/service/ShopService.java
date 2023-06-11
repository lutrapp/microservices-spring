package com.microservices.apishop.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.microservices.apishop.dto.ShopDto;
import com.microservices.apishop.model.Shop;
import com.microservices.apishop.repository.ShopRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopService {
	private final ShopRepository shopRepository;

	public List<ShopDto> getAll() {
		List<Shop> shops = shopRepository.findAll();
		return shops.stream().map(ShopDto::convert).collect(Collectors.toList());
	}

	public List<ShopDto> getByUser(String userIdentifier) {
		List<Shop> shops = shopRepository.findAllByUserIdentifier(userIdentifier);

		return shops.stream().map(ShopDto::convert).collect(Collectors.toList());
	}

	public List<ShopDto> getByDate(ShopDto shopDto) {
		List<Shop> shops = shopRepository.findAllByDateGreaterThan(shopDto.getDate());
		return shops.stream().map(ShopDto::convert).collect(Collectors.toList());
	}

	public ShopDto findById(long ProductId) {
		Optional<Shop> shop = shopRepository.findById(ProductId);
		if (shop.isPresent()) {
			return ShopDto.convert(shop.get());
		}
		return null;
	}

	public ShopDto save(ShopDto shopDto) {
		shopDto.setTotal(shopDto.getItems().stream().map(x -> x.getPrice()).reduce((float) 0, Float::sum));
		Shop shop = Shop.convert(shopDto);
		shop.setDate(LocalDateTime.now());
		shop = shopRepository.save(shop);
		return ShopDto.convert(shop);
	}
}
