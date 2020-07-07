package app.controller;

import app.model.Customer;
import app.service.ICustomerService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private ICustomerService customerService;

	@GetMapping
	public String index(Model model){
		List<Customer> list = customerService.findAll();
		model.addAttribute("list", list);
		return "customers/index";
	}

	@GetMapping("/add")
	public String addGet(){
		return "customers/add";
	}

	@PostMapping("/add")
	public String addPost(@ModelAttribute Customer customer, Model model){
		customerService.save(customer);
		model.addAttribute("mess", "add success.");
		return "customers/add";
	}

	@GetMapping("/edit/{id}")
	public String editGet(@PathVariable Long id, Model model){
		Customer customer = customerService.findById(id);
		model.addAttribute("customer", customer);
		return "customers/edit";
	}

	@PostMapping("/edit/{id}")
	public String editPost(@ModelAttribute Customer customer, Model model){
		customerService.save(customer);
		model.addAttribute("mess", "edit success.");
		model.addAttribute("customer", customer);
		return "customers/edit";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id){
		customerService.delete(id);
		return "redirect:/customers";
	}
}
