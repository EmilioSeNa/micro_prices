package com.inditex.zara.micro_prices.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.inditex.zara.micro_prices.domain.Prices;
import com.inditex.zara.micro_prices.infraestructure.dto.PricesDTO;

class PricesMapperTest {

	private final PricesMapper pricesMapper = Mappers.getMapper(PricesMapper.class);

	@Test
	@DisplayName("Test mapping from Prices entity to PricesDTO")
	void testPricesToPricesDTO() {
		// Given
		Prices prices = Prices.builder().id(1L).brandId(1).startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
				.endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59)).priceList(1).productId(35455).priority(0)
				.price(35.50).currency("EUR").build();

		// When
		PricesDTO pricesDTO = pricesMapper.pricesToPricesDTO(prices);

		// Then
		assertThat(pricesDTO).isNotNull();
		assertThat(pricesDTO.getId()).isEqualTo(prices.getId());
		assertThat(pricesDTO.getBrandId()).isEqualTo(prices.getBrandId());
		assertThat(pricesDTO.getStartDate()).isEqualTo("2020-06-14 00:00:00");
		assertThat(pricesDTO.getEndDate()).isEqualTo("2020-12-31 23:59:59");
		assertThat(pricesDTO.getPriceList()).isEqualTo(prices.getPriceList());
		assertThat(pricesDTO.getProductId()).isEqualTo(prices.getProductId());
		assertThat(pricesDTO.getPriority()).isEqualTo(prices.getPriority());
		assertThat(pricesDTO.getPrice()).isEqualTo(prices.getPrice());
		assertThat(pricesDTO.getCurrency()).isEqualTo(prices.getCurrency());
	}

	@Test
	@DisplayName("Test mapping from PricesDTO to Prices entity")
	void testPricesDTOToPrices() {
		// Given
		PricesDTO pricesDTO = PricesDTO.builder().id(1L).brandId(1).startDate("2020-06-14 00:00:00")
				.endDate("2020-12-31 23:59:59").priceList(1).productId(35455).priority(0).price(35.50).currency("EUR")
				.build();

		// When
		Prices prices = pricesMapper.pricesDTOToPrices(pricesDTO);

		// Then
		assertThat(prices).isNotNull();
		assertThat(prices.getId()).isEqualTo(pricesDTO.getId());
		assertThat(prices.getBrandId()).isEqualTo(pricesDTO.getBrandId());
		assertThat(prices.getStartDate()).isEqualTo(LocalDateTime.of(2020, 6, 14, 0, 0, 0));
		assertThat(prices.getEndDate()).isEqualTo(LocalDateTime.of(2020, 12, 31, 23, 59, 59));
		assertThat(prices.getPriceList()).isEqualTo(pricesDTO.getPriceList());
		assertThat(prices.getProductId()).isEqualTo(pricesDTO.getProductId());
		assertThat(prices.getPriority()).isEqualTo(pricesDTO.getPriority());
		assertThat(prices.getPrice()).isEqualTo(pricesDTO.getPrice());
		assertThat(prices.getCurrency()).isEqualTo(pricesDTO.getCurrency());
	}
}