package com.first;

import com.first.controller.HomeController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//new line
@SpringBootApplication
//@Configuration
//@ComponentScan("com.first")
//@EnableAutoConfiguration
public class FirstBootProjectApplication {


    public static void main(String[] args) {

        //bootstrapping your spring boot application
        SpringApplication.run(FirstBootProjectApplication.class, args);
//		HomeController controller=container.getBean("homeController", HomeController.class);
//		System.out.println(controller);


    }

}
