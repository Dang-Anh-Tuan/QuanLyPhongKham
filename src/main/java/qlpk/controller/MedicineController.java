package qlpk.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import qlpk.model.Medicine;
import qlpk.service.MedicineService;

@Controller
public class MedicineController {
	@Autowired
	private MedicineService medicineService;
	@GetMapping("/medicine")
	public String index(Model model) {
		model.addAttribute("medicine", medicineService.findAll());
		return "QuanLyNhanSu/ListMedicine";
	}
	@GetMapping("/medicine/add")
	public String add(Model model) {
		model.addAttribute("medicine", new Medicine());
		return "QuanLyNhanSu/AddMedicine";
	}
	@GetMapping("/medicine/{id}/edit")
	public String edit(@PathVariable int id, Model model) {
		model.addAttribute("medicine", medicineService.findOne(id));
		return "fragments/quanlynhansu";
	}
	@PostMapping("/medicine/save")
	public String save(@Valid Medicine medicine, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return "fragments/quanlynhansu";
		}
		medicineService.save(medicine);
		redirect.addFlashAttribute("success", "Saved medicine successfully!");
		return "redirect:/medicine";
	}
	@GetMapping("/medicine/{id}/delete")
	public String delete(@PathVariable int id, RedirectAttributes redirect) {
		Medicine med = medicineService.findOne(id);
                medicineService.delete(med);
		redirect.addFlashAttribute("success", "Deleted medicine successfully!");
		return "redirect:/medicine";
	}
	@GetMapping("/list-medicine")
	public String showListMedicine(Model model) {
		List<Medicine> medicine = MedicineService.findAll();
		@ModelAttribute
		model.addAllAttribute("medicine", medicine);
		return "QuanLyNhanSu/ListMedicine";
	}
}