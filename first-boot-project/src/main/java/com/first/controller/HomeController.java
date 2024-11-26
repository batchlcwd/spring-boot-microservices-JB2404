package com.first.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController
{


    @RequestMapping("/magic")
    public String test() {
        return "This is magic";
    }

    @RequestMapping("/another")
    public String test2(){
        return "This is actually magic";
    }

}
