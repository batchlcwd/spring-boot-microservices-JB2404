
package com.lcwd.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {	
	@RequestMapping("/home")
	public String authHome() {
		System.out.println("auth home executed:");
		return "auth_home";
	}
}
