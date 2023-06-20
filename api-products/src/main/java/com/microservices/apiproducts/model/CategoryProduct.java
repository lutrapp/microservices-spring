package com.microservices.apiproducts.model;

import com.microservices.shoppingclient.dto.CategoryProductDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "category")
public class CategoryProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;

	public static CategoryProduct convert(CategoryProductDto categoryProductDto) {
		CategoryProduct categoryProduct = new CategoryProduct();
		categoryProduct.setId(categoryProductDto.getId());
		categoryProduct.setNome(categoryProductDto.getNome());
		return categoryProduct;
	}
}
