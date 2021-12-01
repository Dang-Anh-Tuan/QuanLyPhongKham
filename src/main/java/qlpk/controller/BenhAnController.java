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

import qlpk.entity.BenhAn;
import qlpk.service.BenhAnService;

@Controller
@RequestMapping("/bacsi")
public class BenhAnController {
	@Autowired
	private BenhAnService benhAnService;
	public BenhAnController (BenhAnService benhAnService) {
		this.benhAnService = benhAnService;
	};
	@GetMapping("/list-benhan")
	public String showListThuoc(Model model) {
		List<BenhAn> listBenhAn = benhAnService.getAll();
		model.addAttribute("listBenhAn", listBenhAn);		
		return "BacSi/ListBenhAn";
	}
	
	@GetMapping("/benhan/edit/{id}")
	public String showEditBenhAn(@PathVariable int id, Model model) {
		Optional<BenhAn> optBenhAn = benhAnService.findById(id);
		if(optBenhAn.isPresent()) {
			BenhAn benhAn = optBenhAn.get();
			model.addAttribute("benhan", benhAn);
			return "BacSi/EditBenhAn";
		}
		
		// 404
		return "BacSi/EditBenhAn";
	}
	
	
	@GetMapping("/benhan/delete/{id}")
	public String deleteBenhAn(@PathVariable int id) {
		benhAnService.deleteBenhAn(id);
		// 404
		return "redirect:/bacsi/list-benhan";
	}
	
}