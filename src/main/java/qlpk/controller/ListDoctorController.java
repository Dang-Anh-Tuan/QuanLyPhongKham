package qlpk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ListDoctorController {

	
	@GetMapping("/add-doctor")
	public String showAddFormDoctor() {
		return "QuanLyNhanSu/AddDoctor";
	}
	
	@GetMapping("/list-nurse")
	public String showListNurse() {
		return "QuanLyNhanSu/ListNurse";
	}
	
	@GetMapping("/add-nurse")
	public String showAddFormNurse() {
		return "QuanLyNhanSu/AddNurse";
	}
	
	
	
	@GetMapping("/add-medicine")
	public String showAddFormMedicne(Model model) {
		return "QuanLyNhanSu/AddMedicine";
	}
	
	
}
