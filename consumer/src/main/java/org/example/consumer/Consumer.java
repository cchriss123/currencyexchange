package org.example.consumer;

import org.example.service.CurrencyExchangeService;
import org.example.service.annotation.CurrencyCode;

import java.io.IOException;
import java.util.*;

public class Consumer {

    public static void main(String[] args) throws IOException{

        Scanner scanner = new Scanner(System.in);
        String fromCurrency = "SEK";
        Map<String, CurrencyExchangeService> currencyPrices = new HashMap<>();
        ServiceLoader<CurrencyExchangeService> loader = ServiceLoader.load(CurrencyExchangeService.class);


        for (CurrencyExchangeService service : loader) {
            CurrencyCode annotation = service.getClass().getAnnotation(CurrencyCode.class);
            if (annotation != null) {
                currencyPrices.put(annotation.value(), service);
            }
        }

        System.out.println("Enter the currency code to see currency prices from SEK (USD or EUR):");
        String toCurrency = "";
        while (!currencyPrices.containsKey(toCurrency)) {
            toCurrency = scanner.nextLine().trim().toUpperCase();
            if (!currencyPrices.containsKey(toCurrency)) {
                System.out.println("Invalid input. Please enter a valid currency code (USD or EUR).");
            }
        }

        double currencyPrice = currencyPrices.get(toCurrency).getExchangeRate(fromCurrency, toCurrency);

        System.out.println("Price from " + fromCurrency + " to " + toCurrency + ": " + String.format("%.3f", currencyPrice));


    }
}
