package org.example.provider;

import org.example.service.CurrencyExchangeService;

import java.io.IOException;

public class UsdExchange implements CurrencyExchangeService {

    public String getSupportedCurrency() {
        return "USD";
    }

    @Override
    public double getExchangeRate(String currencyCode, String toCurrencyCode) throws IOException {
        return ExchangeRateProvider.getExchangeRate(currencyCode, toCurrencyCode);
    }

}
