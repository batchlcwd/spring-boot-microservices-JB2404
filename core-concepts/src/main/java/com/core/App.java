package com.core;

import com.core.college.Student;
import com.core.college.Teacher;
import com.core.concepts.Car;
import com.core.concepts.ConfigClass;
import com.core.concepts.Engine;
import com.core.qualifier.Human;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        System.out.println("project started");
        System.out.println("Starting point");

//        ApplicationContext container = new ClassPathXmlApplicationContext("config.xml");

        ApplicationContext container = new AnnotationConfigApplicationContext(ConfigClass.class);
        //container
        Engine engine1 = container.getBean("engine1", Engine.class);
        engine1.startEngine();
        System.out.println("------------");
        Car car = container.getBean("car", Car.class);
        car.start();

        Student student = container.getBean("student", Student.class);
        System.out.println(student);
        student.show();

        Teacher teacher = container.getBean("teacher", Teacher.class);
        System.out.println(teacher);

        Human kartik = container.getBean("human", Human.class);
        kartik.tryColdDrink();


    }
}

/*


The control of creating and managing objects is
 transferred to a container (Spring IoC Container).
 It allows loose coupling between objects.
 Design Principle : Spring IOC Container
 */
