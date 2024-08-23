package com.example.springexample;

import org.springframework.stereotype.Component;


public class MyFirstClass {

    private String myVar;

    public MyFirstClass(String myVar) {
        this.myVar = myVar;
    }

    public String sayHello() {
        return "Hello From the First Class ===> myVar = " + myVar;
    }
}
