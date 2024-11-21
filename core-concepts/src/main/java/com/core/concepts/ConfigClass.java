package com.core.concepts;

import com.core.college.Teacher;
import com.core.qualifier.ColdDrink;
import com.core.qualifier.Pepsi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.core"})
public class ConfigClass {


    @Bean(name = "teacher")
    public Teacher getTeacher() {
        return new Teacher();
    }

    @Bean(name = "pepsi2")
    public ColdDrink getColdDrink() {
        return new Pepsi();
    }

}
