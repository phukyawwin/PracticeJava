package com.example.springexample;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ApplicationConfig {

    //@Bean
    // @Qualifier("bean1")
    @Bean("bean1")
    public MyFirstClass myFirstClass() {
        return new MyFirstClass("First Bean");
    }

    @Bean
    // @Qualifier("bean2")
    public MyFirstClass mySecondClass() {
        return new MyFirstClass("Second Bean");
    }

    @Bean
    // @Primary
    // @Qualifier("bean2")
    public MyFirstClass myThirdClass() {
        return new MyFirstClass("Third Bean");
    }
}
