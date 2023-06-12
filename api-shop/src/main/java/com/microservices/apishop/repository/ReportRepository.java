package com.microservices.apishop.repository;

import java.time.LocalDate;
import java.util.List;

import com.microservices.apishop.dto.ShopReportDto;
import com.microservices.apishop.model.Shop;

public interface ReportRepository {
	public List<Shop> getShopByFilters(LocalDate dataInicio, LocalDate dataFim, Float valorMinimo);

	public ShopReportDto getReportByDate(LocalDate dataInicio, LocalDate dataFim);
}
