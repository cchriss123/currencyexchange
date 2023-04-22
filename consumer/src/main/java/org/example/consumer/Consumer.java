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

        Optional<CurrencyExchangeService> exchangeServiceOptional = loader.stream()
                .map(ServiceLoader.Provider::get)
                .findFirst();

        if (exchangeServiceOptional.isPresent()) {
            CurrencyExchangeService exchangeService = exchangeServiceOptional.get();
            try {
                double exchangeRate = exchangeService.getExchangeRate(fromCurrency, toCurrency);
                System.out.println("Exchange rate from " + fromCurrency + " to " + toCurrency + ": " + String.format("%.4f", exchangeRate));
            }
            catch (IOException e) {
                System.out.println("Error fetching exchange rate: " + e.getMessage());
            }
        }
        else {
            System.out.println("No currency exchange service implementation found.");
        }







//        ServiceLoader<CurrencyExchangeService> loader = ServiceLoader.load(CurrencyExchangeService.class);
//        String fromCurrency = "SEK";




//        ServiceLoader<Greeting> loader = ServiceLoader.load(Greeting.class);
//        for (var greeting : loader) {
//            System.out.println(greeting.sayHello());
//        }
//
//
//     loader.findFirst().ifPresent(greeting -> System.out.println(greeting.sayHello()));
//
//
//
//        System.out.println("Enter the currency code to convert SEK to (e.g., USD or EUR):");
//        String toCurrency = "USD"; //scanner.nextLine().trim().toUpperCase();
//
//        for (CurrencyExchangeService exchangeService : loader) {
//            try {
//                double exchangeRate = exchangeService.getExchangeRate(fromCurrency, toCurrency);
//                System.out.printf("Exchange rate from %s to %s: %.2f%n", fromCurrency, toCurrency, exchangeRate);
//            } catch (IOException e) {
//                System.out.println("Error fetching exchange rate: " + e.getMessage());
//            }
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
