package org.example.consumer;

import org.example.service.CurrencyExchangeService;
import org.example.service.annotation.CurrencyCode;

import java.io.IOException;
import java.util.*;


public class Consumer {

    public static void main(String[] args) throws IOException{


        Scanner scanner = new Scanner(System.in);
        String fromCurrency = "SEK";
        Map<String, CurrencyExchangeService> currencyServices = new HashMap<>();
        ServiceLoader<CurrencyExchangeService> loader = ServiceLoader.load(CurrencyExchangeService.class);


        for (CurrencyExchangeService service : loader) {
            CurrencyCode annotation = service.getClass().getAnnotation(CurrencyCode.class);
            if (annotation != null) {
                currencyServices.put(annotation.value(), service);
            }
        }

        System.out.println("Enter the currency code to see currency prices from SEK (USD or EUR):");

        String toCurrency = "";
        while (!currencyServices.containsKey(toCurrency)) {
            toCurrency = scanner.nextLine().trim().toUpperCase();
            if (!currencyServices.containsKey(toCurrency)) {
                System.out.println("Invalid input. Please enter a valid currency code (USD or EUR).");
            }
        }

        CurrencyExchangeService exchangeService = currencyServices.get(toCurrency);
        double exchangeRate = exchangeService.getExchangeRate(fromCurrency, toCurrency);
        System.out.println("Exchange rate from " + fromCurrency + " to " + toCurrency + ": " + String.format("%.4f", exchangeRate));


    }
}
