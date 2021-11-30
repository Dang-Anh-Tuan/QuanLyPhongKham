package qlpk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DoctorController {
	@GetMapping("/bacsi-list-benhan")
	public String showListBenhAn() {
		return "BacSi/ListBenhAn";
	}
	
	@GetMapping("/khambenh")
	public String showViewKhamBenh() {
		return "BacSi/KhamBenh";
	}
	
	@GetMapping("/kedon")
	public String showViewKeDon() {
		return "BacSi/KeDon";
	}
}
