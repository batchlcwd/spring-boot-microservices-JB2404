package com.result.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }


    @GetMapping("/view-result")
    public String viewResultForm(){
        return "view_result_form";
    }

    @GetMapping("/help")
    public String help(){
        return "help";
    }


}
