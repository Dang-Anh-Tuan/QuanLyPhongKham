package qlpk.controller;


//import java.lang.System.Logger;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mysql.cj.log.Log;

import qlpk.entity.BenhAn;
import qlpk.entity.BenhNhan;
import qlpk.entity.Thuoc;
import qlpk.service.BenhAnService;
import qlpk.service.BenhNhanService;

@Controller
public class BenhAnController {
	@Autowired
	private BenhAnService benhAnService;
	
	@Autowired
	private BenhNhanService benhNhanService;
	
	public BenhAnController (BenhAnService benhAnService, BenhNhanService benhNhanService) {
		this.benhAnService = benhAnService;
		this.benhNhanService = benhNhanService;
	};
	
	@GetMapping("bacsi/list-benhan")
	public String showListBenhAnBS(Model model) {
		List<BenhAn> listBenhAn = benhAnService.getAll();
		model.addAttribute("listBenhAn", listBenhAn);		
		return "BacSi/ListBenhAn";
	}
	
	@GetMapping("yta/list-benhan")
	public String showListBenhAnYT(Model model) {
		List<BenhAn> listBenhAn = benhAnService.getAll();
		model.addAttribute("listBenhAn", listBenhAn);		
		return "YTa/ListBenhAn";
	}
	
	@GetMapping("/yta/benhan/add")
	public String showFormAddBenhAn(Model model) {
		BenhNhan benhNhan = new BenhNhan();
		model.addAttribute("benhNhan", benhNhan);
		return "YTa/AddBenhAn";
	}
	
	@PostMapping("/yta/benhan/add")
	public String handleAddBenhAn(@Valid @ModelAttribute("benhNhan") BenhNhan benhNhan,  BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "YTa/AddBenhAn";
		}
		benhNhanService.saveBenhNhan(benhNhan);
		BenhAn benhAn = new BenhAn();
		benhAn.setBenhNhan(benhNhan);
		benhAnService.saveBenhAn(benhAn);
		return "redirect:/yta/list-benhan";
	}
	
	@GetMapping("yta/benhan/edit/{id}")
	public String showEditBenhAn(@PathVariable int id, Model model) {
		Optional<BenhNhan> optBenhNhan = benhNhanService.findById(id);
		if(optBenhNhan.isPresent()) {
			BenhNhan benhNhan = optBenhNhan.get();
			model.addAttribute("benhNhan", benhNhan);
			return "YTa/EditBenhAn"; 
		}
		
		return "redirect:/404";
	}
	@PostMapping("yta/benhan/edit/{id}") 
	public String handleEditBenhAn(@Valid @ModelAttribute("benhNhan") BenhNhan benhNhan, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "YTa/EditBenhAn";
		}
		benhNhanService.updateBenhNhan(benhNhan);
		return "redirect:/yta/list-benhan";
	}
	
	@GetMapping("yta/benhan/delete/{id}")
	public String deleteBenhAn(@PathVariable int id) {
		Optional<BenhAn> optBenhAn = benhAnService.getById(id);
		if(optBenhAn.isPresent()) {
			benhAnService.deleteBenhAn(id);
			return "redirect:/yta/list-benhan";
		}
			return "redirect:/404";
	}
}