package com.bloomberg.datawarehouse.service;

import com.bloomberg.datawarehouse.entity.Deal;
import com.bloomberg.datawarehouse.exception.DuplicateDealException;
import com.bloomberg.datawarehouse.exception.InvalidRequestException;
import com.bloomberg.datawarehouse.repository.DealRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@Transactional
class DealServiceTests {

    @Autowired
    private DealRepository dealRepository;

    private DealService dealService;

    @BeforeEach
    public void setup() {
        dealService = new DealService(dealRepository);
    }

    @Test
    public void givenDeal_whenCreating_thenShouldSuccess() {
        final Deal expected = getDeal();

        dealService.createDeal(expected);

        final List<Deal> deals = dealService.list();
        assertEquals(1, deals.size());

        final Deal actual = deals.get(0);
        assertEquals(expected.getAmount(), actual.getAmount());
        assertEquals(expected.getTimestamp(), actual.getTimestamp());
        assertEquals(expected.getToCurrency(), actual.getToCurrency());
        assertEquals(expected.getFromCurrency(), actual.getFromCurrency());
    }

    @Test
    public void givenTwoDuplicateDeals_whenSaving_thenShouldThrowException() {
        final Deal deal = getDeal();

        dealService.createDeal(deal);
        final DuplicateDealException duplicateDealException = assertThrows(DuplicateDealException.class, () -> dealService.createDeal(deal));

        final String expectedMessage = "Deal already exist";
        assertEquals(expectedMessage, duplicateDealException.getMessage());
    }

    @Test
    public void givenInvalidCurrencyDeal_whenSaving_thenShouldThrowException() {
        final Deal deal = getDeal();
        deal.setToCurrency("test");

        final InvalidRequestException invalidRequestException = assertThrows(InvalidRequestException.class, () -> dealService.createDeal(deal));

        final String expectedMessage = "Currency test is not valid";
        assertEquals(expectedMessage, invalidRequestException.getMessage());
    }

    private static Deal getDeal() {
        final Deal deal = new Deal();
        deal.setAmount(BigDecimal.valueOf(1000.0));
        deal.setTimestamp(Timestamp.valueOf("2016-11-09 11:44:44.797"));
        deal.setToCurrency("JOD");
        deal.setFromCurrency("USD");
        return deal;
    }
}