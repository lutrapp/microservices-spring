package com.microservices.apiproducts.dto;

import com.microservices.apiproducts.model.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
	@NotBlank
	private String productIdentifier;
	@NotBlank
	private String nome;
	@NotBlank
	private String descricao;
	@NotNull
	private Float preco;
	@NotNull
	private CategoryProductDto category;

	public static ProductDto convert(Product product) {
		ProductDto productDto = new ProductDto();
		productDto.setNome(product.getNome());
		productDto.setPreco(product.getPreco());
		productDto.setProductIdentifier(product.getProductIdentifier());
		productDto.setDescricao(product.getDescricao());
		if (product.getCategoryProduct() != null) {
			productDto.setCategory(CategoryProductDto.convert(product.getCategoryProduct()));
		}
		return productDto;
	}

}
