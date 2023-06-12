package com.microservices.apishop.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.apishop.dto.ShopDto;
import com.microservices.apishop.dto.ShopReportDto;
import com.microservices.apishop.service.ReportService;
import com.microservices.apishop.service.ShopService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/shopping")
@RequiredArgsConstructor
public class ShopController {
	private final ShopService shopService;
	private final ReportService reportService;

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
	public ShopDto newShop(@RequestBody ShopDto shopDto) {
		return shopService.save(shopDto);
	}

	@GetMapping("/search")
	public List<ShopDto> getShopsByFilter(
			@RequestParam(name = "dataInicio", required = true) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataInicio,
			@RequestParam(name = "dataFim", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataFim,
			@RequestParam(name = "valorMinimo", required = false) Float valorMinimo) {
		return reportService.getShopsByFilter(dataInicio, dataFim, valorMinimo);
	}

	@GetMapping("/report")
	public ShopReportDto getReportByDate(
			@RequestParam(name = "dataInicio", required = true) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataInicio,
			@RequestParam(name = "dataFim", required = true) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataFim) {
		return reportService.getReportByDate(dataInicio, dataFim);
	}

}