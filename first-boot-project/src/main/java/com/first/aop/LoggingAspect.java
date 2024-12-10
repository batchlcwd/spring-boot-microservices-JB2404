package com.first.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    //cross cutting concern
    //execution: type of AOP advice - method execution
    // * :matches any return type
    //com.first.services.ProductService: fully qualified class name where method is defined.
    //createProduct:matches the method name
    //(..) : matches any number of arguments
    //execution(<modifier-pattern> <return-type-pattern> <declaring-type-pattern>.<method-name-pattern>(<parameter-pattern>))
    @Before("execution(* com.first.services.ProductService.*(..))")
    public void greeting(){
        System.out.println("hi method starting...");
    }

    @After("execution(* com.first.services.ProductService.*(..))")
    public  void byeBye(){
        System.out.println("bye bye, terminating method");
    }

}
