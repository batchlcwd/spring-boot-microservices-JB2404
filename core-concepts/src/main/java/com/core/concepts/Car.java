package com.core.concepts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("car")
public class Car {
    //manually creating object and injecting to it

    Engine engine;




    public Car(Engine engine) {
        this.engine = engine;
        System.out.println("setting using constructor");
    }

    public Car() {
    }

    public Engine getEngine() {
        return engine;
    }

    //setter method
    @Autowired
    public void setEngine(Engine engine) {
        this.engine = engine;
        System.out.println("setting engine:setter injection");
    }

    public void start() {
        //car start nhi ho sakti hai
        //we need engine
        engine.startEngine();
        System.out.println("car started");
    }
}
