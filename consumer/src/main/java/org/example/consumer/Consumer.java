package org.example.consumer;

import org.example.service.Greeting;

import java.util.ServiceLoader;



public class Consumer {

    public static void main(String[] args) {

        ServiceLoader<Greeting> loader = ServiceLoader.load(Greeting.class);
        for (Greeting greeting : loader) {
            System.out.println(greeting.sayHello());
        }





    }
}
