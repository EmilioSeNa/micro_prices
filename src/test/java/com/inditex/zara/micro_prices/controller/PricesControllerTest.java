package com.inditex.zara.micro_prices.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PricesControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@DisplayName("Test retrieving price via API")
	@ParameterizedTest(name = "Test {index}: productId={0}, brandId={1}, date={2} → expectedPrice={3}")
	@CsvSource({ "35455, 1, '2020-06-14 10:00:00', 35.50", "35455, 1, '2020-06-14 16:00:00', 25.45",
			"35455, 1, '2020-06-14 21:00:00', 35.50", "35455, 1, '2020-06-15 10:00:00', 30.50",
			"35455, 1, '2020-06-16 21:00:00', 38.95" })
	void getPriceAPI(int productId, int brandId, String date, double expectedPrice) throws Exception {
		mockMvc.perform(get("/api/prices").param("date", date).param("productId", String.valueOf(productId))
				.param("brandId", String.valueOf(brandId))).andExpect(status().isOk())
				.andExpect(jsonPath("$.price").value(expectedPrice));
	}
}