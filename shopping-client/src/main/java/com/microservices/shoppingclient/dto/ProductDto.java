package com.microservices.shoppingclient.dto;

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
	private String productIdentifier; //TODO AVALIAR TROCAR O NOME PARA SKU (OU EAN, OU AMBOS) AJUSTAR BANCO DE DADOS
/*
 EXEMPLO: NIK-AF-FEM-BR-35, sendo que:

NIK - fabricante Nike
AF - modelo Air Force
FEM - feminino
BR - cor branca
35 - tamanho 35

o modelo masculino e no tamanho 42, o código, por exemplo, seria NIK-AF-MAS-BR-42.

https://cosmos.bluesoft.com.br/   >> EXEMPLOS EAN
CANETA GEL TRIS INKFINITY 6UN 683379 SUMMIT
NCM: 9608.10.00
GTIN/EAN: 7897476683379
 */
	@NotBlank
	private String nome;
	@NotBlank
	private String descricao;
	@NotNull
	private Float preco;
	@NotNull
	private CategoryProductDto category;


}
