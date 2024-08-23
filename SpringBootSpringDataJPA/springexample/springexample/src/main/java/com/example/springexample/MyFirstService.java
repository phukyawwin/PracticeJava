package com.example.springexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class MyFirstService {

    //@Autowired
    //@Qualifier("mySecondClass")
    //@Qualifier("bean1")
    private MyFirstClass myFirstClass;
    private Environment environment;

    @Autowired
    public MyFirstService(@Qualifier("mySecondClass")
                          MyFirstClass myFirstClass) {
        this.myFirstClass = myFirstClass;
    }
//    @Autowired
//    public void injectDependencies(@Qualifier("mySecondClass") MyFirstClass myFirstClass) {
//        this.myFirstClass = myFirstClass;
//    }

    //    @Autowired
//    public void setMyFirstClass(@Qualifier("mySecondClass") MyFirstClass myFirstClass) {
//        this.myFirstClass = myFirstClass;
//    }
    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public String getJavaVersion() {
        return environment.getProperty("java.version");
    }

    public String getOSName() {
        return environment.getProperty("os.name");
    }

    public String readProp() {
        return environment.getProperty("my.custom.property");
    }

    public String tellAStory() {
        return "The Dependency is saying : " + myFirstClass.sayHello();
    }
}
