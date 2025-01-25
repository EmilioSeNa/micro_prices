package com.inditex.zara.micro_prices.domain;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository interface for accessing price data.
 */
@Repository
public interface PricesRepository extends JpaRepository<Prices, Long> {
    
    /**
     * Finds applicable prices for a given product, brand, and date.
     * Orders them by priority in descending order to select the most relevant price.
     *
     * @param productId The product identifier
     * @param brandId The brand identifier
     * @param date The application date
     * @return A list of applicable prices ordered by priority
     */
    List<Prices> findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
            int productId, int brandId, LocalDateTime date1, LocalDateTime date2);
}
