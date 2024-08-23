package com.example.springexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MyFirstService {

    //@Autowired
    //@Qualifier("mySecondClass")
    //@Qualifier("bean1")
    private MyFirstClass myFirstClass;

    //@Autowired
//    public MyFirstService( //@Qualifier("bean2")
//                           MyFirstClass myFirstClass) {
//        this.myFirstClass = myFirstClass;
//    }
//    @Autowired
//    public void injectDependencies(@Qualifier("mySecondClass") MyFirstClass myFirstClass) {
//        this.myFirstClass = myFirstClass;
//    }

    @Autowired
    public void setMyFirstClass(@Qualifier("mySecondClass") MyFirstClass myFirstClass) {
        this.myFirstClass = myFirstClass;
    }

    public String tellAStory() {
        return "The Dependency is saying : " + myFirstClass.sayHello();
    }
}
