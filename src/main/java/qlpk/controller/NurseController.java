package qlpk.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.validation.Errors;

import qlpk.modelUtil.BacSyLuong;
import qlpk.modelUtil.YtaLuong;
import qlpk.security.User;
import qlpk.entity.Benh;
import qlpk.entity.BenhAn;
import qlpk.entity.BenhNhan;
import qlpk.entity.YTa;
import qlpk.entity.enums.Role;
import qlpk.service.BenhAnService;
import qlpk.service.YTaService;

@Controller
public class NurseController {
	@Autowired
	private YTaService yTaService;

	@Autowired
	private BenhAnService benhAnService;

	public NurseController(YTaService yTaService, BenhAnService benhAnService) {
		this.yTaService = yTaService;
		this.benhAnService = benhAnService;
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
		User taiKhoan = new User();

		model.addAttribute("yTa", yTa);
		model.addAttribute("taikhoan", taiKhoan);
		return "QuanLyNhanSu/AddNurse";

	}

	@PostMapping("/qlns/yta/add")
	public String handleAddYTa(@Valid @ModelAttribute("yTa") YTa yTa, BindingResult result,
			@ModelAttribute("taikhoan") User taiKhoan, Model model) {

		if (result.hasErrors()) {
			return "QuanLyNhanSu/AddNurse";
		}
		taiKhoan.setRole("Role.YTA");
//			 setTK
//			yTa.setTaiKhoan(taiKhoan);
		yTaService.saveYTa(yTa);
		return "redirect:/qlns/yta/ds-yta";

	}

	@GetMapping("/qlns/yta/edit/{id}")
	public String showEditFormYTa(@PathVariable int id, Model model) {
		Optional<YTa> optYTa = yTaService.getById(id);
		// get TaiKhoan map voi Bac Sy
//		model.addAttribute("taikhoan",  taiKhoanService.getByUsername(String username).get());
		User taiKhoan = new User();

		taiKhoan.setRole("Role.YTA");

		if (optYTa.isPresent()) {
			model.addAttribute("yTa", optYTa.get());
			model.addAttribute("taikhoan", taiKhoan);
			return "QuanLyNhanSu/EditNurse";
		}

		// 404
		return "404";
	}

	@PostMapping("/qlns/yta/edit/{id}")
	public String handleEditYTa(@PathVariable int id, @Valid @ModelAttribute("yTa") YTa yTa, BindingResult result,
			@Valid @ModelAttribute("taikhoan") User taiKhoan, Model model) {

		if (result.hasErrors()) {
//			Optional<YTa> optYTa = yTaService.getById(id);
//			model.addAttribute("yTa", optYTa.get());
//			model.addAttribute("taikhoan", taiKhoan);
			return "QuanLyNhanSu/EditNurse";
		} else {
			// setTK
//			yta.setTaiKhoan(taiKhoan);
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

			// fake benh
			Benh benh1 = new Benh();
			benh1.setId(10);
			benh1.setTenBenh("ho");
			Benh benh2 = new Benh();
			benh2.setId(11);
			benh2.setTenBenh("sot");
			List<Benh> dsBenh = Arrays.asList(benh1, benh2);

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
