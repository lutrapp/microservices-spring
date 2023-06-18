package com.microservices.apiproducts.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.microservices.apiproducts.dto.DtoConverter;
import com.microservices.apiproducts.model.Product;
import com.microservices.apiproducts.repository.ProductRepository;
import com.microservices.shoppingclient.dto.ProductDto;
import com.microservices.shoppingclient.exception.CategoryNotFoundException;
import com.microservices.shoppingclient.exception.ProductNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;
//	private final CategoryRepository categoryRepository;

	public List<ProductDto> getAll() {
		List<Product> products = productRepository.findAll();
		return products.stream().map(DtoConverter::convert).collect(Collectors.toList());
	}

	public List<ProductDto> getProductByCategoryId(Long categoryId) {
		List<Product> products = productRepository.getProductByCategory(categoryId);
		return products.stream().map(DtoConverter::convert).collect(Collectors.toList());
	}

	public ProductDto findByProductIdentifier(String productIdentifier) {
		Product product = productRepository
				.findByProductIdentifier(productIdentifier);
		if (product != null) {
			return DtoConverter.convert(product);
		}
		throw new ProductNotFoundException();
	}

	public ProductDto save(ProductDto productDto) {
		//TODO VERIFICAR SE ESTA CORRETO O METODO ABAIXO, OS PARAMETROS
		Boolean existsCategory = productRepository.existsById(productDto.getCategory().getId());
		if(!existsCategory) {
			throw new CategoryNotFoundException();
		}
		
		Product product = productRepository
				.save(Product.convert(productDto));
		return DtoConverter.convert(product);
	}

	public void delete(long productId) throws ProductNotFoundException {
		Optional<Product> product = productRepository
				.findById(productId);
		if (product.isPresent()) {
			productRepository.delete(product.get());
		}
		throw new ProductNotFoundException();
	}

	public ProductDto editProduct(long id, ProductDto dto) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product	not	found"));
		if (dto.getNome() != null || !dto.getNome().isEmpty()) {
			product.setNome(dto.getNome());
		}
		if (dto.getPreco() != null) {
			product.setPreco(dto.getPreco());
		}

		return DtoConverter.convert(productRepository.save(product));
	}

	public Page<ProductDto> getAllPage(Pageable page) {
		Page<Product> users = productRepository.findAll(page);
		return users.map(DtoConverter::convert);
	}

}
