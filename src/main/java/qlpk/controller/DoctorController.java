package qlpk.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.mysql.cj.Session;

import qlpk.entity.BacSy;
import qlpk.entity.Benh;
import qlpk.entity.BenhAn;
import qlpk.entity.BenhNhan;
import qlpk.entity.DonThuoc;
import qlpk.entity.Thuoc;
import qlpk.security.User;
import qlpk.service.BacSyService;
import qlpk.service.BenhAnService;
import qlpk.modelUtil.DetailThuoc;

@Controller
public class DoctorController {

	@Autowired
	private BacSyService bacSyService;
//	@Autowired
//	private TaiKhoanService taiKhoanService;

	@Autowired
	private BenhAnService benhAnService;

	public DoctorController(BacSyService bacSyService, BenhAnService benhAnService) {
		this.bacSyService = bacSyService;
		this.benhAnService = benhAnService;
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
	public String handleAddBacSi(@Valid @ModelAttribute("bacsi") BacSy bacsi, BindingResult result,
			@ModelAttribute("taikhoan") User user) {

		if (result.hasErrors()) {
			return "QuanLyNhanSu/AddDoctor";
		}

		// setTK
		java.util.logging.Logger.getLogger(DoctorController.class.getName()).info(user.getUsername());
		java.util.logging.Logger.getLogger(DoctorController.class.getName()).info(user.getPassword());
		// userService.saveTaiKhoan(taiKhoan);
//		bacsi.setUser(user);

		bacSyService.saveBacSy(bacsi);
		return "redirect:/qlns/bacsi/ds-bacsi";
	}

	@GetMapping("/qlns/bacsi/edit/{id}")
	public String showEditFormBacSi(@PathVariable int id, Model model) {
		Optional<BacSy> optBacSi = bacSyService.getById(id);
		// get TaiKhoan map voi Bac Sy
//		model.addAttribute("taikhoan",  taiKhoanService.getByUsername(String username).get());
		User user = new User();

		// Role role = Role.BACSY;
		// taiKhoan.setRole(role);

		if (optBacSi.isPresent()) {
			model.addAttribute("bacsi", optBacSi.get());
			model.addAttribute("taikhoan", user);
			return "QuanLyNhanSu/EditDoctor";
		}

		return "404";
	}

	@PostMapping("/qlns/bacsi/edit/{id}")
	public String handleEditBacSi(@Valid @ModelAttribute("bacsi") BacSy bacsi, BindingResult result,
			@ModelAttribute("taikhoan") User user) {
		if (result.hasErrors()) {
			return "QuanLyNhanSu/EditDoctor";
		}

		// setTK
//		bacsi.setTaiKhoan(taiKhoan);
		bacSyService.updateBacSy(bacsi);
		return "redirect:/qlns/bacsi/ds-bacsi";
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

	@GetMapping("/bacsi/khambenh/{id}")
	public String showViewKhamBenh(@PathVariable int id, Model model) {
		// get Benh An
		Optional<BenhAn> optBenhAn = benhAnService.getById(id);
		if (optBenhAn.isPresent()) {
			BenhAn benhAn = optBenhAn.get();
			BenhNhan benhNhan = benhAn.getBenhNhan();
			// get All Benh cua Bac Si Kham ( Lấy Bac Si từ session )
//			User userBS = session.get 
//			List<Benh> dsBenh = benhService.getByBacSi(idBacSi);

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

			// model add benh cua bac si
			model.addAttribute("dsBenh", dsBenh);

			// check co benh cua bac si nay chua

			Benh benh = new Benh();
	

//			List<Benh> benhCuaBenhAn = benhService.getByBenhAn(id);
//			for(Benh item : benhCuaBenhAn) {

			// neu benh an nay da co benh cua bac si dang kham

//				if(dsBenh.contains(item)) {
//					benh = benhService.getById(item.getId()).get();
//					idBenhCu = item.getId();
//					model.addAttribute("benh", benh);
//					return "BacSi/KhamBenh";
//				}

//			}
			
			model.addAttribute("benh", benh);
			return "BacSi/KhamBenh";
		}
		return "redirect:/404";

	}

	@PostMapping("/bacsi/khambenh/{id}")
	public String handleKhamBenh(@PathVariable int id, @ModelAttribute("benhAn") BenhAn benhAn,
			@ModelAttribute("benhNhan") BenhNhan benhNhan, @ModelAttribute("benh") Benh benh,
			@ModelAttribute("idBenhCu") Integer idBenhCu) {

		System.out.println(benh.getId());
//		if (idBenhCu != 0) {
//			// xoa benh cu trong danh sach benh cua benh an do
//			if (benh.getId() != 1) {
//				// get id cua benh
//				
//				// benhAnService.updateBenh(id)
//			} else {
//				// benhAnService.updateBenh(id)
//			}
//		}

		return "redirect:/bacsi/list-benhan";
	}

	@GetMapping("/bacsi/kedon/{id}")
	public String showViewKeDon(@PathVariable int id, Model model) {

		// model add benh anh
		// model add ds-thuoc
		Optional<BenhAn> optBenhAn = benhAnService.getById(id);
		if (optBenhAn.isPresent()) {
			BenhAn benhAn = optBenhAn.get();
			BenhNhan benhNhan = benhAn.getBenhNhan();
			// get All Thuoc
//			List<Thuoc> dsThuoc = thuocService.getAll();

			// model add benh an va benh nhanh
			model.addAttribute("benhAn", benhAn);
			model.addAttribute("benhNhan", benhNhan);

			// fake thuoc
			Thuoc thuoc1 = new Thuoc();
			thuoc1.setId(10);
			thuoc1.setTen("panadol");
			Thuoc thuoc2 = new Thuoc();
			thuoc2.setId(11);
			thuoc2.setTen("panadol extra");

			List<Thuoc> dsThuoc = Arrays.asList(thuoc1, thuoc2);

			// model add thuoc
			model.addAttribute("dsThuoc", dsThuoc);

			return "BacSi/KeDon";
		}
		return "redirect:/404";

	}

	@PostMapping("/bacsi/kedon/{id}")
	public String handleKeDon(@PathVariable int id, @RequestBody(required = false) String json, Model model) {

		ObjectMapper mapper = new ObjectMapper();
		try {
			List<DetailThuoc> listDetailThuoc = mapper.readValue(json, new TypeReference<List<DetailThuoc>>() {
			});
			for (DetailThuoc d : listDetailThuoc) {
				System.out.println(d.getIdThuoc() + " - " + d.getLieuDung() + " - " + d.getCachDung());
				return "404";
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		return "redirect:/qlns/bacsi/ds-bacsi";
	}

	@GetMapping(value = { "/bacsi/xembenhan/{id}" })
	public String showBSBenhAn(@PathVariable int id, Model model) {
		// get Benh An
		// get ds benh cua benh an do
		// check benh nao thuoc bac si hien tai
		// model add benh anh
		// model add ds benh cua benh an do ( tru benh cua bac si hien tai kham)
		// model add ("bs-benh") benh cua bac si hien tai kham ( gen ra nut xac nhan
		// khoi )

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

			// fake benh cua bac si
			List<Benh> dsBenhBS = Arrays.asList(benh1);

			// model add benh cua benh an
			model.addAttribute("dsBenhBS", dsBenhBS);

			return "Bacsi/ViewBenhAn";
		}
		return "redirect:/404";
	}

	@PostMapping("/bacsi/xembenhan/{id}")
	public String handleUpdateKhoiBenh(@PathVariable int id, @RequestBody(required = false) String json) {
		if (json != null) {
			try {
				JSONObject jsonObject = new JSONObject(json);
				int idBenh = jsonObject.getInt("id");
				System.out.println(idBenh);
				return "redirect:/bacsi/list-benhan";
			} catch (Exception e) {

			}

			// update khoi benh cho benh an do

			// ******* redirect danh sach benh an *********

		}
		return "redirect:/bacsi/list-benhan";
	}

	@GetMapping(value = { "/bacsi/xemdonthuoc/{id}" })
	public String showBSDonThuoc(@PathVariable int id, Model model) {

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

			return "Bacsi/ViewDonThuoc";
		}
		return "redirect:/404";
	}

}
