package org.example.provider;

import org.example.service.CurrencyExchangeService;
import org.example.service.annotation.CurrencyCode;

import java.io.IOException;

@CurrencyCode("USD")
public class UsdExchange implements CurrencyExchangeService {

    @Override
    public double getExchangeRate(String currencyCode, String toCurrencyCode) throws IOException {
        return ExchangeRateProvider.getExchangeRate(currencyCode, toCurrencyCode);
    }
}
