package com.inditex.zara.micro_prices.infraestructure.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inditex.zara.micro_prices.application.PricesService;
import com.inditex.zara.micro_prices.infraestructure.dto.PricesDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * REST controller for retrieving product prices based on application date,
 * product ID, and brand ID.
 */
@RestController
@RequestMapping("/api/prices")
@RequiredArgsConstructor
@Tag(name = "Prices API", description = "Endpoints for querying product prices")
public class PricesController {

	private final PricesService pricesService;

	/**
	 * Retrieves the applicable price for a given product, brand, and date.
	 *
	 * @param date      The application date (formatted as yyyy-MM-dd HH:mm:ss)
	 * @param productId The product identifier
	 * @param brandId   The brand identifier
	 * @return The applicable price or 404 if none found
	 */
	@Operation(summary = "Get applicable price", description = "Retrieves the applicable price based on the date, product ID, and brand ID.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful response", content = @Content(schema = @Schema(implementation = PricesDTO.class))),
			@ApiResponse(responseCode = "404", description = "Price not found") })
	@GetMapping
	public ResponseEntity<?> getPrice(@RequestParam(name = "date") String date,
			@RequestParam(name = "productId") int productId, @RequestParam(name = "brandId") int brandId) {

		LocalDateTime dateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		Optional<PricesDTO> prices = pricesService.getApplicablePrice(productId, brandId, dateTime);
		return prices.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
}
