package org.example.provider;

import org.example.service.annotation.Language;

@Language("sv")
public class AnnotatedGreeting {
    String hello(){
        return "hej";
    }
}
