package org.example.provider;

import org.example.service.CurrencyExchangeService;

import java.io.IOException;

public class EuroExchange implements CurrencyExchangeService {

    @Override
    public String getSupportedCurrency() {
        return "EUR";
    }

    @Override
    public double getExchangeRate(String currencyCode, String toCurrencyCode) throws IOException {
        return ExchangeRateProvider.getExchangeRate(currencyCode, toCurrencyCode);
    }

}
