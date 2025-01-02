package com.lcwd.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping("/home")
	public String userHome() {
		System.out.println("user home executed:");
		return "user_home";
	}

}
