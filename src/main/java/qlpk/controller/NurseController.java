package qlpk.controller;

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
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.validation.Errors;

import qlpk.dto.UserDTO;
import qlpk.entity.YTa;
import qlpk.entity.enums.Role;
import qlpk.service.UserService;
import qlpk.service.YTaService;

@Controller
public class NurseController {
	@Autowired
	private YTaService yTaService;
	@Autowired
	private UserService userService;

	public NurseController(YTaService yTaService, UserService userService) {
		this.yTaService = yTaService;
		this.userService = userService;
	}

	@GetMapping("/qlns/yta/ds-yta")
	public String showListYTa(Model model) {
		List<YTa> dsYTa = yTaService.findAll();
		model.addAttribute("dsYTa", dsYTa);
		return "QuanLyNhanSu/ListNurse";
	}

	@GetMapping("/qlns/yta/add")
	public String showAddFormYTa(Model model) {
		YTa yTa = new YTa();
		UserDTO user = new UserDTO();;

		model.addAttribute("yTa", yTa);
		model.addAttribute("taikhoan", user);
		return "QuanLyNhanSu/AddNurse";

	}

	@PostMapping("/qlns/yta/add")
	public String handleAddYTa(
			@Valid @ModelAttribute("yTa") YTa yTa,
			BindingResult result,
			@ModelAttribute("taikhoan") UserDTO userDTO) {

		if (result.hasErrors()) {
			return "QuanLyNhanSu/AddNurse";
		}
		userDTO.setRole(Role.YTA);
		userService.save(userDTO);
		yTaService.saveYTa(yTa, userDTO);
		return "redirect:/qlns/yta/ds-yta";

	}

	@GetMapping("/qlns/yta/edit/{id}")
	public String showEditFormYTa(@PathVariable int id, Model model) {
		Optional<YTa> optYTa = yTaService.getById(id);
		// get TaiKhoan map voi Bac Sy
//		model.addAttribute("taikhoan",  taiKhoanService.getByUsername(String username).get());
		UserDTO userDTO;

		if (optYTa.isPresent()) {
			userDTO = userService.getUserByID(optYTa.get().getUser().getId());
			model.addAttribute("yTa", optYTa.get());
			model.addAttribute("taikhoan", userDTO);
			return "QuanLyNhanSu/EditNurse";
		}

		// 404
		return "404";
	}

	@PostMapping("/qlns/yta/edit/{id}")
	public String handleEditYTa(@PathVariable int id, @Valid @ModelAttribute("yTa") YTa yTa,
								@Valid @ModelAttribute("taikhoan") UserDTO userDTO, Model model, Errors errors) {

		if (errors.hasErrors()) {
//			Optional<YTa> optYTa = yTaService.getById(id);
//			model.addAttribute("yTa", optYTa.get());
//			model.addAttribute("taikhoan", taiKhoan);
			return "QuanLyNhanSu/EditNurse";
		} else {
			userService.save(userDTO);
			yTaService.updateYTa(yTa);
			return "redirect: /qlns/yta/ds-yta";
		}

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
