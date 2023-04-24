package org.example.provider.currencyexchange;

import org.example.provider.ExchangeRateProvider;
import org.example.service.CurrencyExchangeService;
import org.example.service.annotation.CurrencyCode;

import java.io.IOException;

@CurrencyCode("JPY")
public class JpyExchange implements CurrencyExchangeService {

    @Override
    public double getExchangeRate(String currencyCode, String toCurrencyCode) throws IOException {
        return ExchangeRateProvider.getExchangeRate(currencyCode, toCurrencyCode);
    }
}
