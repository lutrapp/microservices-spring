package com.microservices.apiproducts.dto;

import com.microservices.apiproducts.model.CategoryProduct;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryProductDto {
	@NotNull
	private Long id;
	private String nome;

	public static CategoryProductDto convert(CategoryProduct categoryProduct) {
		CategoryProductDto categoryProductDto = new CategoryProductDto();
		categoryProductDto.setId(categoryProduct.getId());
		categoryProductDto.setNome(categoryProduct.getNome());
		return categoryProductDto;
	}
}
