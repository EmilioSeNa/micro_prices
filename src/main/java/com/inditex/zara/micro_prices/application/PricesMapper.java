package com.inditex.zara.micro_prices.application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.inditex.zara.micro_prices.domain.Prices;
import com.inditex.zara.micro_prices.infraestructure.dto.PricesDTO;

/**
 * Mapper to convert Prices entity to PricesDTO and vice versa.
 */
@Mapper(componentModel = "spring")
public interface PricesMapper {

	PricesMapper INSTANCE = Mappers.getMapper(PricesMapper.class);

	@Mapping(target = "startDate", expression = "java(formatLocalDateTime(prices.getStartDate()))")
	@Mapping(target = "endDate", expression = "java(formatLocalDateTime(prices.getEndDate()))")
	PricesDTO pricesToPricesDTO(Prices prices);

	@Mapping(target = "startDate", expression = "java(parseLocalDateTime(pricesDTO.getStartDate()))")
	@Mapping(target = "endDate", expression = "java(parseLocalDateTime(pricesDTO.getEndDate()))")
	Prices pricesDTOToPrices(PricesDTO pricesDTO);

	default String formatLocalDateTime(LocalDateTime dateTime) {
		return dateTime != null ? dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : null;
	}

	default LocalDateTime parseLocalDateTime(String dateTime) {
		return dateTime != null ? LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
				: null;
	}
}