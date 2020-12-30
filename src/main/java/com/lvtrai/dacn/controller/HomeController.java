package com.lvtrai.dacn.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.lvtrai.dacn.model.Product;
import com.lvtrai.dacn.service.ProductService;

@Controller
public class HomeController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping(value = {"/home", "/"}) 
	public String home(Model model) {
		List<Product> products = productService.findFirstProducts();
		model.addAttribute("products", products);
		return "homepage";
	}
	
	

}
