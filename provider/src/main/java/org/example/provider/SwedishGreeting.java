package org.example.provider;

import org.example.service.Greeting;

public class SwedishGreeting implements Greeting{
    public SwedishGreeting() {  // This constructor is called by the ServiceLoader
        System.out.println("SwedishGreeting constructor");

    }




    @Override
    public String sayHello() {
        return "Hej";
    }


}
