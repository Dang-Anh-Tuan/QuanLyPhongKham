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

import qlpk.dto.UserDTO;
import qlpk.entity.BacSy;
import qlpk.entity.User;
import qlpk.service.BacSyService;
import qlpk.entity.enums.Role;
import qlpk.service.UserService;

@Controller
public class DoctorController {

	@Autowired
	private BacSyService bacSyService;
	@Autowired
	private UserService userService;

	public DoctorController(BacSyService bacSyService, UserService userService) {
		this.bacSyService = bacSyService;
		this.userService = userService;
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
		UserDTO user = new UserDTO();
		model.addAttribute("bacsi", bacSi);
		model.addAttribute("taikhoan", user);

		return "QuanLyNhanSu/AddDoctor";

	}

	@PostMapping("/qlns/bacsi/add")
	public RedirectView handleAddBacSi(@ModelAttribute("bacsi") BacSy bacsi,
			@ModelAttribute("taikhoan") UserDTO userDTO) {
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("/qlns/bacsi/ds-bacsi");

		userDTO.setRole(Role.BACSY);
		userService.save(userDTO);
		bacSyService.saveBacSy(bacsi, userDTO);

		return redirectView;
	}

	@GetMapping("/qlns/bacsi/edit/{id}")
	public String showEditFormBacSi(@PathVariable int id, Model model) {
		Optional<BacSy> optBacSi = bacSyService.getById(id);
		UserDTO userDTO;

		if (optBacSi.isPresent()) {
			userDTO = userService.getUserByID(optBacSi.get().getUser().getId());
			model.addAttribute("bacsi", optBacSi.get());
			model.addAttribute("taikhoan", userDTO);

			return "QuanLyNhanSu/EditDoctor";
		}

		// 404
		return "404";
	}

	@PostMapping("/qlns/bacsi/edit/{id}")
	public RedirectView handleEditBacSi(@ModelAttribute("bacsi") BacSy bacsi,
			@ModelAttribute("taikhoan") UserDTO userDTO) {

		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("/qlns/bacsi/ds-bacsi");

		userService.save(userDTO);
		bacSyService.updateBacSy(bacsi);

		return redirectView;
	}
	
	@GetMapping("/qlns/bacsi/delete/{id}")
	public RedirectView handleDeleteBacSi(@PathVariable int id, Model model) {
		Optional<BacSy> optBacSi = bacSyService.getById(id);
		
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("/qlns/bacsi/ds-bacsi");
		
		if (optBacSi.isPresent()) {
			userService.delete(optBacSi.get().getUser());
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
