package com.bloomberg.datawarehouse.util;

import com.bloomberg.datawarehouse.exception.InvalidRequestException;

import java.util.Currency;

public final class CurrencyUtil {

    public static void checkCurrencyString(final String currency) {
        try {
            Currency.getInstance(currency);
        } catch (final Exception exception) {
            throw new InvalidRequestException(String.format("Currency %s is not valid", currency));
        }
    }
}
