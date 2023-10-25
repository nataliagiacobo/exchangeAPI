package br.ada.exchangeAPI.utils;

import br.ada.exchangeAPI.controller.exception.CurrencyException;
import br.ada.exchangeAPI.service.QuotationService;

import java.math.BigDecimal;

public class OrderCalculate {
    public static BigDecimal bidValue(String currency) throws CurrencyException {
        if ("USD".equals(currency) || "EUR".equals(currency)) {
            return QuotationService.getBid(currency);
        } else {
            throw new CurrencyException("Currency must be USD or EUR");
        }
    }

    public static BigDecimal operationCostValue(BigDecimal exchangeAmount, BigDecimal exchangeRate) {
        BigDecimal cost = exchangeAmount.multiply(exchangeRate);
        return cost;
    }
}
