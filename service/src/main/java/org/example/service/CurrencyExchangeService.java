package org.example.service;

import java.io.IOException;

public interface CurrencyExchangeService {
    double getExchangeRate(String fromCurrencyCode, String toCurrencyCode) throws IOException;
}
