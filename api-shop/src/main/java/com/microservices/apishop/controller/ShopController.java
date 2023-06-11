package com.microservices.apishop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.apishop.dto.ShopDto;
import com.microservices.apishop.service.ShopService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/shopping")
@RequiredArgsConstructor
public class ShopController {
	private final ShopService shopService;

	@GetMapping("/all")
	public List<ShopDto> getShops() {
		return shopService.getAll();
	}

	@GetMapping("/shopByUser/{userIdentifier}")
	public List<ShopDto> getShops(@PathVariable String userIdentifier) {
		return shopService.getByUser(userIdentifier);
	}

	@GetMapping("/shopByDate")
	public List<ShopDto> getShops(@RequestBody ShopDto shopDto) {

		return shopService.getByDate(shopDto);
	}

	@GetMapping("/{id}")
	public ShopDto findById(@PathVariable Long id) {
		return shopService.findById(id);
	}

	@PostMapping("/new")
	public ShopDto newShop( @RequestBody ShopDto shopDto) {
		return shopService.save(shopDto);
	}
}