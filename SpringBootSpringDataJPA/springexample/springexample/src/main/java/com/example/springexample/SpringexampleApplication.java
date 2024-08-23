package com.example.springexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringexampleApplication {

    public static void main(String[] args) {

        var ctx = SpringApplication.run(SpringexampleApplication.class, args);

        MyFirstService myFirstService = ctx.getBean(MyFirstService.class);
        System.out.println(myFirstService.tellAStory());
//        System.out.println(myFirstService.getJavaVersion());
//        System.out.println(myFirstService.getOSName());
//        System.out.println(myFirstService.readProp());
    }
}
