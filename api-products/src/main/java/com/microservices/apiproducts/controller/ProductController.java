package com.microservices.apiproducts.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.apiproducts.service.ProductService;
import com.microservices.shoppingclient.dto.ProductDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService productService;

	@GetMapping("/all")
	public List<ProductDto> getProducts() {
		return productService.getAll();
	}

	@GetMapping("/category/{categoryId}")
	public List<ProductDto> getProductByCategory(@PathVariable Long categoryId) {
		return productService.getProductByCategoryId(categoryId);
	}

	@GetMapping("/{productIdentifier}")
	public ProductDto findById(@PathVariable String productIdentifier) {
		return productService.findByProductIdentifier(productIdentifier);
	}

	@PostMapping("/newProduct")
	public ProductDto newProduct(@Valid @RequestBody ProductDto productDto) {
		return productService.save(productDto);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		productService.delete(id);
	}

	@PostMapping("/{id}")
	public ProductDto editProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
		return productService.editProduct(id, productDto);
	}

	@GetMapping("/pageable")
	public Page<ProductDto> getProductsPage(Pageable pageable) {
		return productService.getAllPage(pageable);
	}

}
