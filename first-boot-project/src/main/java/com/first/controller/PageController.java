package com.first.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {

    @RequestMapping("/about")
    public String about() {
        System.out.println("rendering about page");
        return "about";
    }

    @RequestMapping("/services")
    public String services() {
        System.out.println("rendering service page");
        return "services";
    }

}
