package com.devsuperior.dsmeta.dto;

public class SellerAmountDTO {
	
	private String name;
	private Double soma;
	
	public SellerAmountDTO() {
	}

	public SellerAmountDTO(String name, Double soma) {
		this.name = name;
		this.soma = soma;
	}

	public String getName() {
		return name;
	}

	public Double getSoma() {
		return soma;
	}
}
