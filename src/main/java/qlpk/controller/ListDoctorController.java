package qlpk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ListDoctorController {
	@GetMapping("/list-doctor")
	public String showListDoctor() {
		return "QuanLyNhanSu/ListDoctor";
	}
	
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
	
	@GetMapping("/list-medicine")
	public String showListMedicine() {
		return "QuanLyNhanSu/ListMedicine";
	}
	
	@GetMapping("/add-medicine")
	public String showAddFormMedicne() {
		return "QuanLyNhanSu/AddMedicine";
	}
}
