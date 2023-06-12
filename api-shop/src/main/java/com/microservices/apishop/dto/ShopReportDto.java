package com.microservices.apishop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShopReportDto {
	private	Integer	count;
	private	Double	total;
	private	Double	mean;
}
