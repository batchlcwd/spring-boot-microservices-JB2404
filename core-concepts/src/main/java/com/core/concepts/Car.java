package com.core.concepts;

public class Car {
    //manually creating object and injecting to it
    Engine engine;


    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void start() {
        //car start nhi ho sakti hai
        //we need engine
        engine.startEngine();
        System.out.println("car started");
    }
}
