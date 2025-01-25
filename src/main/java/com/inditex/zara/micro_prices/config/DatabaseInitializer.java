package com.inditex.zara.micro_prices.config;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.inditex.zara.micro_prices.domain.Prices;
import com.inditex.zara.micro_prices.domain.PricesRepository;

import lombok.RequiredArgsConstructor;

/**
 * Class to initialize database with sample data at application startup.
 */
@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

	private final PricesRepository pricesRepository;

	@Override
	public void run(String... args) {
		pricesRepository.save(new Prices(null, 1, LocalDateTime.of(2020, 6, 14, 0, 0),
				LocalDateTime.of(2020, 12, 31, 23, 59), 1, 35455, 0, 35.50, "EUR"));

		pricesRepository.save(new Prices(null, 1, LocalDateTime.of(2020, 6, 14, 15, 0),
				LocalDateTime.of(2020, 6, 14, 18, 30), 2, 35455, 1, 25.45, "EUR"));

		pricesRepository.save(new Prices(null, 1, LocalDateTime.of(2020, 6, 15, 0, 0),
				LocalDateTime.of(2020, 6, 15, 11, 0), 3, 35455, 1, 30.50, "EUR"));

		pricesRepository.save(new Prices(null, 1, LocalDateTime.of(2020, 6, 15, 16, 0),
				LocalDateTime.of(2020, 12, 31, 23, 59), 4, 35455, 1, 38.95, "EUR"));
	}
}