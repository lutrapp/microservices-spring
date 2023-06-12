package com.microservices.apishop.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.microservices.apishop.dto.ShopDto;
import com.microservices.apishop.dto.ShopReportDto;
import com.microservices.apishop.model.Shop;
import com.microservices.apishop.repository.ShopRepository;
import com.microservices.apishop.repositoryImpl.ReportRepositoryImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportService {

	private final ReportRepositoryImpl reportRepositoryImpl;

	public List<ShopDto> getShopsByFilter(LocalDate dataInicio, LocalDate dataFim, Float valorMinimo) {
		List<Shop> shops = reportRepositoryImpl.getShopByFilters(dataInicio, dataFim, valorMinimo);
		return shops.stream().map(ShopDto::convert).collect(Collectors.toList());
	}

	public ShopReportDto getReportByDate(LocalDate dataInicio, LocalDate dataFim) {
		return reportRepositoryImpl.getReportByDate(dataInicio, dataFim);
	}
}
