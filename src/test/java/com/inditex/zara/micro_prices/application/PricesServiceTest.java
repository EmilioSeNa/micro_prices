package com.inditex.zara.micro_prices.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.inditex.zara.micro_prices.infraestructure.dto.PricesDTO;

@SpringBootTest
@ActiveProfiles("test")
class PricesServiceTest {

	@Autowired
	private PricesService pricesService;

	@DisplayName("Test retrieving applicable price for a given product, brand, and date")
	@ParameterizedTest(name = "Test {index}: productId={0}, brandId={1}, date={2} → expectedPrice={3}")
	@CsvSource({ "35455, 1, '2020-06-14T10:00:00', 35.50", // Test 1
			"35455, 1, '2020-06-14T16:00:00', 25.45", // Test 2
			"35455, 1, '2020-06-14T21:00:00', 35.50", // Test 3
			"35455, 1, '2020-06-15T10:00:00', 30.50", // Test 4
			"35455, 1, '2020-06-16T21:00:00', 38.95" // Test 5
	})
	void getApplicablePrice(int productId, int brandId, String date, double expectedPrice) {
		LocalDateTime applicationDate = LocalDateTime.parse(date);

		Optional<PricesDTO> result = pricesService.getApplicablePrice(productId, brandId, applicationDate);

		assertTrue(result.isPresent(), "No price found for the given date");
		assertEquals(expectedPrice, result.get().getPrice(), 0.01, "Returned price is not as expected");
	}
}
