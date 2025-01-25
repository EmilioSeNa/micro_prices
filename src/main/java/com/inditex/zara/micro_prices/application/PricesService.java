package com.inditex.zara.micro_prices.application;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inditex.zara.micro_prices.domain.PricesRepository;
import com.inditex.zara.micro_prices.infraestructure.dto.PricesDTO;

import lombok.RequiredArgsConstructor;

/**
 * Service responsible for handling price queries.
 */
@Service
@RequiredArgsConstructor
public class PricesService {

	private final PricesRepository pricesRepository;
	private final PricesMapper pricesMapper;

	/**
	 * Retrieves the applicable price for a given product, brand, and date.
	 *
	 * @param productId The product identifier
	 * @param brandId   The brand identifier
	 * @param date      The application date
	 * @return The most relevant price DTO or empty if none is found
	 */
	public Optional<PricesDTO> getApplicablePrice(int productId, int brandId, LocalDateTime date) {
		return pricesRepository
				.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
						productId, brandId, date, date)
				.stream().findFirst().map(pricesMapper::pricesToPricesDTO);
	}
}
