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
import org.springframework.web.bind.annotation.*;
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
	@RequestMapping("/")
	public String showList() {
		return "404";
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
		System.out.println("day la cau sout");
		model.addAttribute("bacsi", bacSi);
		model.addAttribute("taikhoan", user);
		return "QuanLyNhanSu/AddDoctor";

	}

	@PostMapping("/qlns/bacsi/add")
	public RedirectView handleAddBacSi(@ModelAttribute("bacsi") BacSy bacsi,
			@ModelAttribute("taikhoan") UserDTO userDTO) {
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("/qlns/bacsi/ds-bacsi");
		System.out.println("day la ham posst");
		// setTK
		java.util.logging.Logger.getLogger(DoctorController.class.getName()).info(userDTO.getUserName());
		java.util.logging.Logger.getLogger(DoctorController.class.getName()).info(userDTO.getPassword());
		User user = new User();
		user = convertDTOToEntity(userDTO);

		user.setRole(Role.BACSY);
		bacsi.setUser(user);
		userService.save(user);
		bacSyService.saveBacSy(bacsi);

		return redirectView;
	}

	@GetMapping("/qlns/bacsi/edit/{id}")
	public String showEditFormBacSi(@PathVariable int id, Model model) {
		Optional<BacSy> optBacSi = bacSyService.getById(id);
		// get TaiKhoan map voi Bac Sy
//		model.addAttribute("taikhoan",  taiKhoanService.getByUsername(String username).get());

		UserDTO userDTO = new UserDTO();

		User user = new User();

		//Role role = Role.BACSY;
		//taiKhoan.setRole(role);

		if (optBacSi.isPresent()) {
			userDTO = convertEntityToDTO(userService.getUserByName(optBacSi.get().getUser().getUserName()));
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
		// setTK

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

	private User convertDTOToEntity(UserDTO userDTO){
		User user = new User();
		user.setUserName(userDTO.getUserName());
		user.setPassword(userDTO.getPassword());
		user.setRole(Role.BACSY);
		return user;
	}
	private UserDTO convertEntityToDTO(User user){
		UserDTO userDTO = new UserDTO();
		userDTO.setUserName(user.getUserName());
		userDTO.setPassword(user.getPassword());
		return userDTO;
	}
}
