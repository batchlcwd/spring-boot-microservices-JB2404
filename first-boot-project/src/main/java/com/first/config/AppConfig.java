package com.first.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pack.Student;

@Configuration
@ComponentScan("pack")
public class AppConfig {

    @Bean
    public Student studentBean() {
        return new Student()
                ;
    }

}
