package org.example.consumer;

import org.example.service.CurrencyExchangeService;
import org.example.service.annotation.CurrencyCode;

import java.io.IOException;
import java.util.*;

public class Consumer {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        Map<String, CurrencyExchangeService> loaderMap = new HashMap<>();
        ServiceLoader<CurrencyExchangeService> loader = ServiceLoader.load(CurrencyExchangeService.class);

        populateLoadMap(loaderMap, loader);


        System.out.println("\nWelcome to my currency price service!\n");

        System.out.println("Enter your currency code.");
        System.out.println(loaderMap.keySet());
        String fromCurrency = scanner.nextLine().trim().toUpperCase();
        if(!loaderMap.containsKey(fromCurrency)) {
            System.out.println(fromCurrency + " is not available. Currency code is set to SEK.");
            fromCurrency = "SEK.";
        }


        System.out.println("Enter the currency code to see currency prices for " + fromCurrency);
        System.out.println(loaderMap.keySet());
        String toCurrency = "";
        while (!loaderMap.containsKey(toCurrency)) {
            toCurrency = scanner.nextLine().trim().toUpperCase();
            if (!loaderMap.containsKey(toCurrency)) {
                System.out.println("Invalid input. Please enter a valid currency code (USD or EUR).");
            }
        }

        double currencyPrice = loaderMap.get(toCurrency).getExchangeRate(fromCurrency, toCurrency);

        System.out.println("Price from " + fromCurrency + " to " + toCurrency + ": " + String.format("%.3f", currencyPrice));


    }

    private static void populateLoadMap(Map<String, CurrencyExchangeService> currencyPrices, ServiceLoader<CurrencyExchangeService> loader) {
        for (CurrencyExchangeService service : loader) {
            CurrencyCode annotation = service.getClass().getAnnotation(CurrencyCode.class);
            if (annotation != null) {
                currencyPrices.put(annotation.value(), service);
            }
        }
    }
}
