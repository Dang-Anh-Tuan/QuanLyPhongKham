package qlpk.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import ch.qos.logback.classic.Logger;
import qlpk.entity.BacSy;
import qlpk.entity.Benh;
import qlpk.entity.BenhAn;
import qlpk.entity.TaiKhoan;
import qlpk.service.BacSyService;
import qlpk.entity.enums.Role;

@Controller
public class DoctorController {

	@Autowired
	private BacSyService bacSyService;

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
		TaiKhoan taiKhoan = new TaiKhoan();
		Role role = Role.BACSY;
		taiKhoan.setRole(role);

		model.addAttribute("bacsi", bacSi);
		model.addAttribute("taikhoan", taiKhoan);
		return "QuanLyNhanSu/AddDoctor";

	}

	@PostMapping("/qlns/bacsi/add")
	public RedirectView handleAddBacSi(@ModelAttribute("bacsi") BacSy bacsi,
			@ModelAttribute("taikhoan") TaiKhoan taiKhoan) {
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("/qlns/bacsi/ds-bacsi");
		try {
			// setTK
//			bacsi.setTaiKhoan(taiKhoan);
			java.util.logging.Logger.getLogger(DoctorController.class.getName()).info(taiKhoan.getUserName());
			java.util.logging.Logger.getLogger(DoctorController.class.getName()).info(taiKhoan.getPassword());
			bacSyService.saveBacSy(bacsi);
		} catch (Exception e) {
			java.util.logging.Logger.getLogger(DoctorController.class.getName()).info("Sai cú pháp ngày");
		}

		return redirectView;
	}

	@GetMapping("/qlns/bacsi/edit/{id}")
	public String showEditFormBacSi(@PathVariable int id, Model model) {
		Optional<BacSy> optBacSi = bacSyService.getById(id);
		// get TaiKhoan map voi Bac Sy
//		model.addAttribute("taikhoan",  taiKhoanService.getByUsername(String username).get());
		TaiKhoan taiKhoan = new TaiKhoan();

		Role role = Role.BACSY;
		taiKhoan.setRole(role);

		if (optBacSi.isPresent()) {
			model.addAttribute("bacsi", optBacSi.get());
			model.addAttribute("taikhoan", taiKhoan);
			return "QuanLyNhanSu/EditDoctor";
		}

		return "404";
	}

	@PostMapping("/qlns/bacsi/edit/{id}")
	public RedirectView handleEditBacSi(@ModelAttribute("bacsi") BacSy bacsi,
			@ModelAttribute("taikhoan") TaiKhoan taiKhoan) {
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

		redirectView.setUrl("/404");
		return redirectView;
	}

	@GetMapping("/khambenh/{id}")
	public String showViewKhamBenh(@PathVariable int id, Model model) {
		// get Benh An
		// get All Benh cua Bac Si Kham ( Lấy Bac Si từ session )
		// model add benh an
		// model add benh cua bac si
		// check co benh cua bac si nay chua
		// neu chua model add new Benh()
		// neu co model add benh do
		return "BacSi/KhamBenh";
	}

	@PostMapping("/bacsi/khambenh/{id}")
	public String handleKhamBenh(@PathVariable int id, @ModelAttribute("benhAn") BenhAn benhAn,
			@ModelAttribute("benh") BacSy bacSy) {
		// get id cua benh
		// neu co
		// add thêm bệnh vào bệnh án theo id benh
		// neu khong
		return "BacSi/KhamBenh";
	}

	@GetMapping("/bacsi/kedon/{id}")
	public String showViewKeDon(@PathVariable int id, Model model) {
		// get Benh An
		// get All Thuoc
		// model add benh anh
		// model add ds-thuoc

		return "BacSi/KeDon";
	}

	@PostMapping("/bacsi/kedon/{id}")
	public String handleKeDon(@PathVariable int id) {
		// get Benh An
		// get JSON
		// convert JSON -> Map<idThuoc, soLuong>
		// update
		return "BacSi/KeDon";
	}

	@GetMapping("/bacsi/xembenhan/{id}")
	public String showBSBenhAn(@PathVariable int id, Model model) {
		// get Benh An
		// get ds benh cua benh an do
		// check benh nao thuoc bac si hien tai
		// model add benh anh
		// model add ds benh cua benh an do ( tru benh cua bac si hien tai kham)
		// model add ("bs-benh") benh cua bac si hien tai kham ( gen ra nut xac nhan
		// khoi )
		return "BacSi/KeDon";
	}

	@PostMapping("/bacsi/xembenhan/{id}")
	public String handleUpdateKhoiBenh(@PathVariable int id, @ModelAttribute("bs-benh") Benh benh) {
		// update khoi benh cho benh an do
		return "BacSi/KeDon";
	}
}
