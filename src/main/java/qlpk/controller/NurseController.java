package qlpk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class NurseController {
	@GetMapping("/list-benhan")
	public String showListDoctor() {
		return "Yta/ListBenhAn";
	}
	
	@GetMapping("/add-benhan")
	public String showAddFormDoctor() {
		return "Yta/AddBenhAn";
	}

}
