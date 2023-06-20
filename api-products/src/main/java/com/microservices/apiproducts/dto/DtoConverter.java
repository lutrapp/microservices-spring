package com.microservices.apiproducts.dto;

import com.microservices.apiproducts.model.CategoryProduct;
import com.microservices.apiproducts.model.Product;
import com.microservices.shoppingclient.dto.CategoryProductDto;
import com.microservices.shoppingclient.dto.ProductDto;

public class DtoConverter {

	public static CategoryProductDto convert(CategoryProduct categoryProduct) {
		CategoryProductDto categoryProductDto = new CategoryProductDto();
		categoryProductDto.setId(categoryProduct.getId());
		categoryProductDto.setNome(categoryProduct.getNome());
		return categoryProductDto;
	}

	public static ProductDto convert(Product product) {
		ProductDto productDto = new ProductDto();
		productDto.setNome(product.getNome());
		productDto.setPreco(product.getPreco());
		productDto.setProductIdentifier(product.getProductIdentifier());
		productDto.setDescricao(product.getDescricao());
		if (product.getCategoryProduct() != null) {
//			productDto.setCategory(CategoryProductDto.convert(product.getCategoryProduct()));
			productDto.setCategory(DtoConverter.convert(product.getCategoryProduct()));
		}
		return productDto;
	}

}
