package com.microservices.apiproducts.model;

import com.microservices.apiproducts.dto.ProductDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Float preco;
	private String descricao;
	private String productIdentifier;
	@ManyToOne
	@JoinColumn(name = "category_id")
	private CategoryProduct categoryProduct;

	public static Product convert(ProductDto productDto) {
		Product product = new Product();
		product.setNome(productDto.getNome());
		product.setPreco(productDto.getPreco());
		product.setDescricao(productDto.getDescricao());
		product.setProductIdentifier(productDto.getProductIdentifier());
		if (productDto.getCategory() != null) {
			product.setCategoryProduct(CategoryProduct.convert(productDto.getCategory()));
		}
		return product;
	}

}
