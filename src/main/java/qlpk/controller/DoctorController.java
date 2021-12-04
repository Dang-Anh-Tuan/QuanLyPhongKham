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
import qlpk.security.User;
import qlpk.service.BacSyService;

@Controller
public class DoctorController {

	@Autowired
	private BacSyService bacSyService;
//	@Autowired
//	private TaiKhoanService taiKhoanService;

	public DoctorController(BacSyService bacSyService) {
		this.bacSyService = bacSyService;
	}

	@GetMapping("/qlns/bacsi/ds-bacsi")
	public String showListBacSi(Model model) {
		List<BacSy> dsBacSi = bacSyService.getAll();
		model.addAttribute("dsBacSi", dsBacSi);
		return "QuanLyNhanSu/ListDoctor";
	}

	@GetMapping("/qlns/bacsi/add")
	public String showAddFormBacSi(Model model) {
		BacSy bacSi = new BacSy();
		User user = new User();
		user.setRole("Role.BACSY");

		model.addAttribute("bacsi", bacSi);
		model.addAttribute("taikhoan", user);
		return "QuanLyNhanSu/AddDoctor";

	}

	@PostMapping("/qlns/bacsi/add")
	public RedirectView handleAddBacSi(@ModelAttribute("bacsi") BacSy bacsi,
			@ModelAttribute("taikhoan") User user) {
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("/qlns/bacsi/ds-bacsi");
		// setTK
		bacsi.setUser(user);
		java.util.logging.Logger.getLogger(DoctorController.class.getName()).info(user.getUsername());
		java.util.logging.Logger.getLogger(DoctorController.class.getName()).info(user.getPassword());
		//taiKhoanService.saveTaiKhoan(taiKhoan);
		bacsi.setUser(user);
		bacSyService.saveBacSy(bacsi);

		return redirectView;
	}

	@GetMapping("/qlns/bacsi/edit/{id}")
	public String showEditFormBacSi(@PathVariable int id, Model model) {
		Optional<BacSy> optBacSi = bacSyService.getById(id);
		// get TaiKhoan map voi Bac Sy
//		model.addAttribute("taikhoan",  taiKhoanService.getByUsername(String username).get());
		User user = new User();

		//Role role = Role.BACSY;
		//taiKhoan.setRole(role);

		if (optBacSi.isPresent()) {
			model.addAttribute("bacsi", optBacSi.get());
			model.addAttribute("taikhoan", user);
			return "QuanLyNhanSu/EditDoctor";
		}

		// 404
		return "404";
	}

	@PostMapping("/qlns/bacsi/edit/{id}")
	public RedirectView handleEditBacSi(@ModelAttribute("bacsi") BacSy bacsi,
			@ModelAttribute("taikhoan") User user) {
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("/qlns/bacsi/ds-bacsi");
		// setTK
//		bacsi.setTaiKhoan(taiKhoan);
		bacSyService.updateBacSy(bacsi);
		return redirectView;
	}
	
	@GetMapping("/qlns/bacsi/delete/{id}")
	public RedirectView handleDeleteBacSi(@PathVariable int id, Model model) {
		Optional<BacSy> optBacSi = bacSyService.getById(id);
		
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("/qlns/bacsi/ds-bacsi");
		
		if (optBacSi.isPresent()) {
			bacSyService.deleteBacSy(id);
			return redirectView;
		}

		// 404
		redirectView.setUrl("/404");
		return redirectView;
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
