package com.mvc.example.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/home")
    public String adminHome(Model model)
    {
        //network: ai:
        String title = "Admin Home";
        //list db fetch:
        List<String> userList = List.of("Akash", "Vivek", "Akshansh");

        //we added our data to model:
        model.addAttribute("title", title);
        model.addAttribute("userList", userList);

        model.addAttribute("currentDate", LocalDate.now());

        return "admin/home";
    }

}
