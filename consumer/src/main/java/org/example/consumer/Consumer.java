package org.example.consumer;

import org.example.service.Greeting;
import org.example.service.annotation.Language;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.stream.Collectors;


public class Consumer {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, IOException, NoSuchMethodException, InstantiationException {

//        ServiceLoader<Greeting> loader = ServiceLoader.load(Greeting.class);
//        for (var greeting : loader) {
//            System.out.println(greeting.sayHello());
//        }


        Set<Class> classes = findAllClasses("org.example.provider");

        for (var c: classes){
           var annotation = (Language) c.getAnnotation(Language.class);
           if (annotation!=null){
               System.out.println(annotation.value());
               var o = c.getConstructor().newInstance();

               var methods = c.getMethods();
               for (var m :methods){
                   if (m.getParameterTypes().equals(String.class) && m.getParameterCount() == 0 && !m.getName().equals("toString")){
                       var s = (String) m.invoke(o);
                       System.out.println(s);
                   }
               }
           }
        }
    }

    public static Set<Class> findAllClasses(String packageName) {

        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
    }

    private static Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "." + className.substring(0, className.lastIndexOf('.')));
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
