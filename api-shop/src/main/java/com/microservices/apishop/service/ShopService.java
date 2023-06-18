package com.microservices.apishop.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.apishop.dto.DtoConverter;
//import com.microservices.apishop.dto.ShopDto;
import com.microservices.apishop.model.Shop;
import com.microservices.apishop.repository.ShopRepository;
import com.microservices.shoppingclient.dto.ItemDto;
import com.microservices.shoppingclient.dto.ProductDto;
import com.microservices.shoppingclient.dto.ShopDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopService {
	
	@Autowired
	private ShopRepository shopRepository;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;
	
//	private final ShopRepository shopRepository;

	public List<ShopDto> getAll() {
		List<Shop> shops = shopRepository.findAll();
//		return shops.stream().map(ShopDto::convert).collect(Collectors.toList());
		return shops.stream().map(DtoConverter::convert).collect(Collectors.toList());
	}

	public List<ShopDto> getByUser(String userIdentifier) {//cpf
		List<Shop> shops = shopRepository.findAllByUserIdentifier(userIdentifier);

		return shops.stream().map(DtoConverter::convert).collect(Collectors.toList());
	}

	//TODO method not working
	public List<ShopDto> getByDate(LocalDateTime date) {
		List<Shop> shops = shopRepository.findAllByDateGreaterThan(date);
		return shops.stream().map(DtoConverter::convert).collect(Collectors.toList());
	}

	public ShopDto findById(long ProductId) {
		Optional<Shop> shop = shopRepository.findById(ProductId);
		if (shop.isPresent()) {
			return DtoConverter.convert(shop.get());
		}
		return null;
	}

	public ShopDto save(ShopDto shopDto) {
		
		if(userService
				.getUserByCpf(shopDto.getUserIdentifier()) == null) {
			return null;
		}
		
		if(!validateProducts(shopDto.getItems())) {
			return null;
		}
		
		shopDto.setTotal(shopDto.getItems()
				.stream()
				.map(x -> x.getPrice())
				.reduce((float) 0, Float::sum));
		Shop shop = Shop.convert(shopDto);
		shop.setDate(LocalDateTime.now());
		shop = shopRepository.save(shop);
		return DtoConverter.convert(shop);
	}

	private boolean validateProducts(List<ItemDto> itens) {
		for(ItemDto item : itens) {
			ProductDto productDto = productService
					.getProductByIdentifier(item.getProductIdentifier());
			if(productDto == null) {
				return false;
			}
			item.setPrice(productDto.getPreco());	
		}
		return true;
	}
	

}
