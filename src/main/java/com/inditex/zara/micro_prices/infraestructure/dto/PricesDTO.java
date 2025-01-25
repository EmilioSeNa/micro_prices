package com.inditex.zara.micro_prices.infraestructure.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for exposing price data via API.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PricesDTO {

	private Long id;
	private int brandId;
	private String startDate;
	private String endDate;
	private int priceList;
	private int productId;
	private int priority;
	private double price;
	private String currency;
}
