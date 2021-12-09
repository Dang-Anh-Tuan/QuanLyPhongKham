package qlpk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import qlpk.dto.auth.param.LoginFormParam;

@Controller
@RequestMapping({"/", "/login"})
public class LoginController {
	@GetMapping
	public String showLoginForm() {
		return "Login";
	}
	@PostMapping
	public String postLoginForm(@ModelAttribute LoginFormParam loginFormParam){
		System.out.println(loginFormParam.toString());
		return "Login";
	}
}
