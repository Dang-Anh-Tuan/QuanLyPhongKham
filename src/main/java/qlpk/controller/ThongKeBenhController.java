package qlpk.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import qlpk.modelUtil.ThongkeBenhDetail;
import qlpk.service.BenhService;

@Controller
public class ThongKeBenhController {
	
	@Autowired
	private BenhService benhService;
	
	public ThongKeBenhController(BenhService benhService) {
		this.benhService = benhService;
	}
	
	@GetMapping("/qlns/thongkebenh")
	public String showThongKe(Model model) {
		
		// Fake ThongKeBenh
		ThongkeBenhDetail tk1 = new ThongkeBenhDetail();
		tk1.setBenh(benhService.getById(1).get());
		tk1.setSoCa(10);
		ThongkeBenhDetail tk2 = new ThongkeBenhDetail();
		tk2.setBenh(benhService.getById(2).get());
		tk2.setSoCa(5);

		
		List<ThongkeBenhDetail> thongKeBenh = Arrays.asList(tk1, tk2);
		model.addAttribute("thongKeBenh", thongKeBenh);
		return "QuanLyNhanSu/ViewThongKe";
	}
}
