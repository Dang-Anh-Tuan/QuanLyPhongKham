package qlpk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import qlpk.dto.UserDTO;
import qlpk.entity.Benh;
import qlpk.entity.BenhAn;
import qlpk.entity.BenhNhan;
import qlpk.entity.YTa;
import qlpk.entity.enums.Role;
import qlpk.modelUtil.YtaLuong;
import qlpk.service.BenhAnService;
import qlpk.service.BenhService;
import qlpk.service.UserService;
import qlpk.service.YTaService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class NurseController {
	@Autowired
	private YTaService yTaService;

	@Autowired
	private BenhAnService benhAnService;
	@Autowired
	private UserService userService;
	@Autowired
	private BenhService benhService;

	public NurseController(YTaService yTaService, BenhAnService benhAnService,
						   UserService userService, BenhService benhService) {
		this.yTaService = yTaService;
		this.benhAnService = benhAnService;
		this.userService = userService;
		this.benhService = benhService;
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
	public String handleEditYTa(@PathVariable int id, @Valid @ModelAttribute("yTa") YTa yTa, BindingResult result,
								@Valid @ModelAttribute("taikhoan") UserDTO userDTO, Model model, Errors errors) {

		if (errors.hasErrors()) {
//			Optional<YTa> optYTa = yTaService.getById(id);
//			model.addAttribute("yTa", optYTa.get());
//			model.addAttribute("taikhoan", taiKhoan);
			return "QuanLyNhanSu/EditNurse";
		} else {
//			userService.save(userDTO);
			yTaService.updateYTa(yTa);
			return "redirect:/qlns/yta/ds-yta";
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

	// xem bệnh án
	@GetMapping(value = { "/yta/xembenhan/{id}" })
	public String showBSBenhAn(@PathVariable int id, Model model) {

		Optional<BenhAn> optBenhAn = benhAnService.getById(id);
		if (optBenhAn.isPresent()) {
			BenhAn benhAn = optBenhAn.get();
			BenhNhan benhNhan = benhAn.getBenhNhan();
			// get All Thuoc
//			List<Thuoc> dsThuoc = thuocService.getAll();

			// model add benh an va benh nhanh
			model.addAttribute("benhAn", benhAn);
			model.addAttribute("benhNhan", benhNhan);


			Optional<Benh> benh1 = benhService.getById(benhAn.getIdBenh());
			List<Benh> dsBenh = Arrays.asList(benh1.get());

			// model add benh cua benh an
			model.addAttribute("dsBenh", dsBenh);

			return "YTa/ViewBenhAn";
		}
		return "redirect:/404";
	}
	
	

	@GetMapping(value = { "/yta/xemdonthuoc/{id}" })
	public String showYTaDonThuoc(@PathVariable int id, Model model) {

		Optional<BenhAn> optBenhAn = benhAnService.getById(id);
		if (optBenhAn.isPresent()) {
			BenhAn benhAn = optBenhAn.get();
			BenhNhan benhNhan = benhAn.getBenhNhan();
			// get All Thuoc
//			List<Thuoc> dsThuoc = thuocService.getAll();

			// model add benh an va benh nhanh
			model.addAttribute("benhAn", benhAn);
			model.addAttribute("benhNhan", benhNhan);

			// get donThuoc

			// model add don thuoc
//			model.addAttribute("donThuoc", donThuoc);

			return "YTa/ViewDonThuoc";
		}
		return "redirect:/404";
	}
	
	@PostMapping(value = { "/yta/xemdonthuoc/{id}" })
	public String handleYTaDonThuoc(@PathVariable int id, Model model) {

		Optional<BenhAn> optBenhAn = benhAnService.getById(id);
		if (optBenhAn.isPresent()) {
			
			// xoa don thuoc trong benh an nay

			return "redirect:/yta/list-benhan";
		}
		return "redirect:/404";
	}
	@GetMapping("/yta/luong")
	public String tinhLuongYta(@RequestParam int id, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date sdate, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date edate, Model model){
		List<YtaLuong> listLuong = yTaService.tinhLuongYta(sdate, edate);
		System.err.println("55555555555555555555555555555");
		model.addAttribute("listLuong", listLuong);
		System.err.println("4444444444444444444444444444444");
//		System.out.println(listLuong);
		return "QuanLyNhanSu/Salary";
	}
}
