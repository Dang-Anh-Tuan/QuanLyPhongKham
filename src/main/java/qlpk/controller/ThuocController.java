package qlpk.controller;


import java.lang.System.Logger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mysql.cj.log.Log;

import qlpk.entity.Thuoc;
import qlpk.service.ThuocService;

@Controller
@RequestMapping("/qlns")
public class ThuocController {
	@Autowired
	private ThuocService thuocService;
	public ThuocController (ThuocService thuocService) {
		this.thuocService = thuocService;
	};
	@GetMapping("/thuoc/ds-thuoc")
	public String showListThuoc(Model model) {
		List<Thuoc> listThuoc = thuocService.getAll();
		model.addAttribute("listThuoc", listThuoc);		
		return "QuanLyNhanSu/ListMedicine";
	}
	
	@GetMapping("/thuoc/edit/{id}")
	public String showEditThuoc(@PathVariable int id, Model model) {
		Optional<Thuoc> optThuoc = thuocService.findById(id);
		if(optThuoc.isPresent()) {
			Thuoc thuoc = optThuoc.get();
			model.addAttribute("thuoc", thuoc);
			return "QuanLyNhanSu/EditMedicine";
		}
		
		// 404
		return "QuanLyNhanSu/ListMedicine";
	}
	
	
	@GetMapping("/thuoc/delete/{id}")
	public String deleteThuoc(@PathVariable int id) {
		thuocService.deleteThuoc(id);
		// 404
		return "redirect:/qlns/thuoc/ds-thuoc";
	}
	
}