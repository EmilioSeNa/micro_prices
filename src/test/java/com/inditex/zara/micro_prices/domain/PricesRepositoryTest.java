package com.inditex.zara.micro_prices.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class PricesRepositoryTest {

	@Autowired
	private PricesRepository pricesRepository;

	@DisplayName("Test price search in real database")
	@ParameterizedTest(name = "Test {index}: product={0}, brand={1}, date={2} → expected={3}")
	@CsvSource({ "35455, 1, '2020-06-14T10:00:00', 35.50", "35455, 1, '2020-06-14T16:00:00', 25.45",
			"35455, 1, '2020-06-14T21:00:00', 35.50", "35455, 1, '2020-06-15T10:00:00', 30.50",
			"35455, 1, '2020-06-16T21:00:00', 38.95" })
	void findApplicablePrice(int productId, int brandId, String date, double expectedPrice) {
		LocalDateTime applicationDate = LocalDateTime.parse(date);
		List<Prices> result = pricesRepository
				.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
						productId, brandId, applicationDate, applicationDate);

		assertFalse(result.isEmpty(), "No price found for the given date");
		assertEquals(expectedPrice, result.get(0).getPrice(), 0.01, "Returned price is not as expected");
	}
}