package qlpk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping({"/", "/login"})
public class LoginController {
	@GetMapping
	public String showDashBoard(){
		return "404";
	}
	@GetMapping("/login")
	public String showLoginForm() {
		return "Login";
	}
}
