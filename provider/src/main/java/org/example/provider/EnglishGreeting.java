package org.example.provider;

import org.example.service.Greeting;

public class EnglishGreeting implements Greeting {

    public EnglishGreeting() {  // This constructor is called by the ServiceLoader
        System.out.println("EnglishGreeting constructor");

    }




    @Override
    public String sayHello() {
        return "Hello";
    }

}
