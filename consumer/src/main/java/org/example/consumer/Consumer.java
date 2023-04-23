package org.example.consumer;

import org.example.service.CurrencyExchangeService;
import org.example.service.Greeting;
import org.example.service.annotation.Language;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;


public class Consumer {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, IOException, NoSuchMethodException, InstantiationException {


        Set<String> supportedCurrencies = new HashSet<>(Arrays.asList("USD", "EUR"));
        ServiceLoader<CurrencyExchangeService> loader = ServiceLoader.load(CurrencyExchangeService.class);
        String fromCurrency = "SEK";

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the currency code to see currency prices from SEK (USD or EUR):");

        String toCurrency = "";
        while (!supportedCurrencies.contains(toCurrency)) {
            toCurrency = scanner.nextLine().trim().toUpperCase();
            if (!supportedCurrencies.contains(toCurrency)) {
                System.out.println("Invalid input. Please enter a valid currency code (USD or EUR).");
            }
        }
        final String finalToCurrency = toCurrency;


        loader.stream()
                .map(ServiceLoader.Provider::get)
                .filter(service -> finalToCurrency.equals(service.getSupportedCurrency()))
                .findFirst()
                .ifPresentOrElse(
                        exchangeService -> {
                            try {
                                double exchangeRate = exchangeService.getExchangeRate(fromCurrency, finalToCurrency);
                                System.out.println("Exchange rate from " + fromCurrency + " to " + finalToCurrency + ": " + String.format("%.4f", exchangeRate));
                            } catch (IOException e) {
                                System.out.println("Error fetching exchange rate: " + e.getMessage());
                            }
                        },
                        () -> System.out.println("No currency exchange service implementation found for the requested currency.")
                );





       }





//        Set<Class> classes = findAllClasses("org.example.provider");
//
//        for (var c: classes){
//           var annotation = (Language) c.getAnnotation(Language.class);
//           if (annotation!=null){
//               System.out.println(annotation.value());
//               var o = c.getConstructor().newInstance();
//
//               var methods = c.getMethods();
//               for (var m :methods){
//                   if (m.getParameterTypes().equals(String.class) && m.getParameterCount() == 0 && !m.getName().equals("toString")){
//                       var s = (String) m.invoke(o);
//                       System.out.println(s);
//                   }
//               }
//           }
//        }
//    }
//
//    public static Set<Class> findAllClasses(String packageName) {
//
//        InputStream stream = ClassLoader.getSystemClassLoader()
//                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
//        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
//
//        return reader.lines()
//                .filter(line -> line.endsWith(".class"))
//                .map(line -> getClass(line, packageName))
//                .collect(Collectors.toSet());
//    }
//
//    private static Class getClass(String className, String packageName) {
//        try {
//            return Class.forName(packageName + "." + className.substring(0, className.lastIndexOf('.')));
//        }
//        catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}
