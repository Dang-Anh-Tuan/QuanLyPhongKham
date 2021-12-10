package qlpk.controller;

import java.text.SimpleDateFormat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import qlpk.modelUtil.DateLuong;

@Controller
public class TinhLuongController {

	@GetMapping("/qlns/xemluong-bs")
	public String showViewTinhLuong(Model model) {
		DateLuong dateLuong = new DateLuong();
		model.addAttribute("dateLuong", dateLuong);
		return "QuanLyNhanSu/SalaryBacSi";
	}
	
	@PostMapping("/qlns/xemluong-bs")
	public String resultTinhLuong(@ModelAttribute("dateLuong") DateLuong dateLuong, Model model) {
		System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(dateLuong.getNgayBD()));
		System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(dateLuong.getNgayKT()));
		
		// model add ds dsBacSiLuong
		return "QuanLyNhanSu/SalaryResultBacSi";
	}
	

	@GetMapping("/qlns/xemluong-yta")
	public String showViewTinhLuongYTa(Model model) {
		DateLuong dateLuong = new DateLuong();
		model.addAttribute("dateLuong", dateLuong);
		return "QuanLyNhanSu/SalaryBacSi";
	}
	
	@PostMapping("/qlns/xemluong-yta")
	public String resultTinhLuongYTa(@ModelAttribute("dateLuong") DateLuong dateLuong, Model model) {
		System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(dateLuong.getNgayBD()));
		System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(dateLuong.getNgayKT()));
		
		// model add ds dsBacSiLuong
		return "QuanLyNhanSu/SalaryBacSi";
	}
}
