package org.example.provider;

import org.example.service.Greeting;

public class EnglishGreeting implements Greeting {
    @Override
    public String sayHello() {
        return "Hello";
    }

}
