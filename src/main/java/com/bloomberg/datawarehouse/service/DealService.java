package com.bloomberg.datawarehouse.service;

import com.bloomberg.datawarehouse.entity.Deal;
import com.bloomberg.datawarehouse.exception.DuplicateDealException;
import com.bloomberg.datawarehouse.repository.DealRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.bloomberg.datawarehouse.util.CurrencyUtil.checkCurrencyString;

@Slf4j
@Service
@RequiredArgsConstructor
public class DealService {

    private final DealRepository dealRepository;

    public List<Deal> list() {
        log.info("List Request is received");
        return dealRepository.findAll();
    }

    public void createDeal(final Deal deal) {
        validateRequest(deal);

        if (isDealExist(deal)) {
            throw new DuplicateDealException("Deal already exist");
        }

        dealRepository.save(deal);

        log.info("Deal has been saved successfully with id %d");
    }

    private boolean isDealExist(final Deal deal) {
        return dealRepository.existsAllByAmountAndTimestampAndFromCurrencyAndToCurrency(deal.getAmount(), deal.getTimestamp(), deal.getFromCurrency(), deal.getToCurrency());
    }

    private void validateRequest(final Deal deal) {
        checkCurrencyString(deal.getToCurrency());
        checkCurrencyString(deal.getFromCurrency());
    }
}
