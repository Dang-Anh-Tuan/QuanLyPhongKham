package qlpk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import qlpk.dto.auth.param.LoginFormParam;

@Controller
public class LoginController {
	@GetMapping("/login")
	public String showLoginForm() {
		return "Login";
	}
//	@PostMapping
//	public String postLoginForm(@ModelAttribute LoginFormParam loginFormParam){
//		return "redirect:/qlns/bacsi/ds-bacsi";
//	}
}
