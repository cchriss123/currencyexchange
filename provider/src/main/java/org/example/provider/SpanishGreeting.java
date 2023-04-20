package org.example.provider;

import org.example.service.Greeting;

public class SpanishGreeting implements Greeting {



    public SpanishGreeting() {
        System.out.println("SpanishGreeting constructor");

    }
    @Override
    public String sayHello() {
        return "Hola";
    }

}
