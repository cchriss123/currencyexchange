package org.example.consumer;

import org.example.service.Greeting;
import org.example.service.annotation.Language;

import java.lang.reflect.InvocationTargetException;
import java.util.ServiceLoader;
import java.util.Set;


public class Consumer {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        ServiceLoader<Greeting> loader = ServiceLoader.load(Greeting.class);

        Set<Class> classes = findAllClasses("org.example.provider");

        for (var c: classes){
           var annotation = (Language) c.getAnnotation(Language.class);
           if (annotation!=null){
               System.out.println(annotation.value());
               var methods = c.getMethods();
               for(var m :methods){
                   if(m.getParameterTypes().equals(String.class) && m.getParameterCount() == 0) {
                     //  var s = (String) m.invoke(c);
                       var s = m.invoke(c);

                       if (s instanceof String string)
                           System.out.println(string);
                   }
               }

           }
        }




        for (var greeting : loader) {
            System.out.println(greeting.sayHello());
        }

    }

    private static Set<Class> findAllClasses(String s) {
        return Set.of();
    }
}
