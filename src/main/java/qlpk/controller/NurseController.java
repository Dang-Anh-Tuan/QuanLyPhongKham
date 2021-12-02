package qlpk.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import qlpk.entity.BacSy;
import qlpk.entity.TaiKhoan;
import qlpk.entity.YTa;
import qlpk.entity.enums.Role;
import qlpk.service.BacSyService;
import qlpk.service.YTaService;


@Controller
public class NurseController {
	@Autowired
	private YTaService yTaService;

	public NurseController(YTaService yTaService) {
		this.yTaService = yTaService;
	}

	@GetMapping("/qlns/yta/ds-yta")
	public String showListYTa(Model model) {
		List<YTa> dsYTa = yTaService.getAll();
		model.addAttribute("dsYTa", dsYTa);
		return "QuanLyNhanSu/ListNurse";
	}

	@GetMapping("/qlns/yta/add")
	public String showAddFormYTa(Model model) {
		YTa yTa = new YTa();
		TaiKhoan taiKhoan = new TaiKhoan();
		Role role = Role.YTA;
		taiKhoan.setRole(role);

		model.addAttribute("yTa", yTa);
		model.addAttribute("taikhoan", taiKhoan);
		return "QuanLyNhanSu/AddNurse";

	}

	@PostMapping("/qlns/yta/add")
	public RedirectView handleAddYTa(@ModelAttribute("yTa") YTa yTa,
			@ModelAttribute("taikhoan") TaiKhoan taiKhoan) {
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("/qlns/yta/ds-yta");
		// setTK
//		yTa.setTaiKhoan(taiKhoan);
		java.util.logging.Logger.getLogger(NurseController.class.getName()).info(taiKhoan.getUserName());
		java.util.logging.Logger.getLogger(NurseController.class.getName()).info(taiKhoan.getPassword());
		yTaService.saveYTa(yTa);
		return redirectView;
	}

	@GetMapping("/qlns/yta/edit/{id}")
	public String showEditFormYTa(@PathVariable int id, Model model) {
		Optional<YTa> optYTa = yTaService.getById(id);
		// get TaiKhoan map voi Bac Sy
//		model.addAttribute("taikhoan",  taiKhoanService.getByUsername(String username).get());
		TaiKhoan taiKhoan = new TaiKhoan();

		Role role = Role.YTA;
		taiKhoan.setRole(role);

		if (optYTa.isPresent()) {
			model.addAttribute("yTa", optYTa.get());
			model.addAttribute("taikhoan", taiKhoan);
			return "QuanLyNhanSu/EditNurse";
		}

		// 404
		return "404";
	}

	@PostMapping("/qlns/yta/edit/{id}")
	public RedirectView handleEditYTa(@ModelAttribute("yTa") YTa yTa,
			@ModelAttribute("taikhoan") TaiKhoan taiKhoan) {
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("/qlns/yta/ds-yta");
		// setTK
//		bacsi.setTaiKhoan(taiKhoan);
		yTaService.updateYTa(yTa);
		return redirectView;
	}
	
	@GetMapping("/qlns/yta/delete/{id}")
	public RedirectView handleDeleteBacSi(@PathVariable int id, Model model) {
		Optional<YTa> optYTa = yTaService.getById(id);
		
		RedirectView redirectView = new RedirectView();
	
		if (optYTa.isPresent()) {
			redirectView.setUrl("/qlns/yta/ds-yta");
			yTaService.deleteYTa(id);
			return redirectView;
		}

		// 404
		redirectView.setUrl("/404");
		return redirectView;
	}
	
	
	
	// phat thuoc, tiem thuoc
	// thanh toán viện phí 

}
