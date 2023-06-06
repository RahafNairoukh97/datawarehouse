package com.bloomberg.datawarehouse.repository;

import com.bloomberg.datawarehouse.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Repository
public interface DealRepository extends JpaRepository<Deal, Long> {
    boolean existsAllByAmountAndTimestampAndFromCurrencyAndToCurrency(BigDecimal amount, Timestamp timestamp, String fromCurrency, String toCurrency);

}
