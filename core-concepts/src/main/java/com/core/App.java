package com.core;

import com.core.concepts.Car;
import com.core.concepts.Engine;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        System.out.println("project started");
        System.out.println("Starting point");

        ApplicationContext container = new ClassPathXmlApplicationContext("config.xml");

        //container
        Engine engine1 = container.getBean("engine1", Engine.class);
        engine1.startEngine();
        System.out.println("------------");
        Car car = container.getBean("car", Car.class);
        car.start();


    }
}

/*


The control of creating and managing objects is
 transferred to a container (Spring IoC Container).
 It allows loose coupling between objects.
 Design Principle : Spring IOC Container
 */
