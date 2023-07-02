package com.microservices.apishop.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.microservices.shoppingclient.dto.ProductDto;

import reactor.core.publisher.Mono;

@Service
public class ProductService {
	@Value("${PRODUCT_API_URL:http://localhost:8080}")
	private String productApiURL;
//	private String productApiURL = "http://localhost:8080";
	public ProductDto getProductByIdentifier(String productIdentifier) {
		try {
			WebClient webClient = WebClient.builder()
					.baseUrl(productApiURL)
					.build();
			
			Mono<ProductDto> product = webClient.get()
					.uri("/product/" + productIdentifier)
					.retrieve()
					.bodyToMono(ProductDto.class);
					
				return product.block();
		}catch (Exception e) {
			throw new RuntimeException("Product not found");
		}
	}
}
