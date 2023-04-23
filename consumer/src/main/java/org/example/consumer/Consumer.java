package org.example.consumer;

import org.example.service.CurrencyExchangeService;
import org.example.service.annotation.CurrencyCode;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;


public class Consumer {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, IOException, NoSuchMethodException, InstantiationException {


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
//}
