package com.inditex.zara.micro_prices.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a price entity that contains pricing information for a specific
 * product within a date range.
 */
@Entity
@Table(name = "PRICES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prices {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "brand_id", nullable = false)
	private int brandId;

	@Column(name = "start_date", nullable = false)
	private LocalDateTime startDate;

	@Column(name = "end_date", nullable = false)
	private LocalDateTime endDate;

	@Column(name = "price_list", nullable = false)
	private int priceList;

	@Column(name = "product_id", nullable = false)
	private int productId;

	@Column(name = "priority", nullable = false)
	private int priority;

	@Column(name = "price", nullable = false)
	private double price;

	@Column(name = "currency", nullable = false)
	private String currency;
}