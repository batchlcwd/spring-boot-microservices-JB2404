package com.first.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pack.Student;

@RestController
public class HomeController
{

    @Autowired
    private Student student;

    @RequestMapping("/magic")
    public String test() {
        return "This is magic";
    }

    @RequestMapping("/another")
    public String test2(){
        return "This is actually magic";
    }

}
