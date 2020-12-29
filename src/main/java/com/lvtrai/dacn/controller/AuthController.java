package com.lvtrai.dacn.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.lvtrai.dacn.model.User;
import com.lvtrai.dacn.service.UserService;
import com.lvtrai.dacn.service.impl.UserSecurityService;

import java.util.Arrays;

import javax.validation.Valid;

@Controller
public class AuthController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserSecurityService userSecurityService;
	
	@RequestMapping("/login")
	public String log(Model model) {
		model.addAttribute("usernameExists", model.asMap().get("usernameExists"));
		model.addAttribute("emailExists", model.asMap().get("emailExists"));
		model.addAttribute("address", model.asMap().get("address"));
		model.addAttribute("phone", model.asMap().get("phone"));
		return "myAccount";
	}
	
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String newUserPost(@Valid @ModelAttribute("user") User user, BindingResult bindingResults,
			@ModelAttribute("new-password") String password, RedirectAttributes redirectAttributes, Model model) {
		model.addAttribute("email", user.getEmail());
		model.addAttribute("username", user.getUsername());
		boolean invalidFields = false;
		if (bindingResults.hasErrors()) {
			return "redirect:/login";
		}
		if (userService.findByUsername(user.getUsername()) != null) {
			redirectAttributes.addFlashAttribute("usernameExists", true);
			invalidFields = true;
		}
		if (userService.findByEmail(user.getEmail()) != null) {
			redirectAttributes.addFlashAttribute("emailExists", true);
			invalidFields = true;
		}
		if (invalidFields) {
			return "redirect:/login";
		}
		user = userService.createUser(user.getUsername(), password, user.getEmail(), Arrays.asList("ROLE_KHACHHANG"));
		userSecurityService.authenticateUser(user.getUsername());
		return "redirect:/my-profile";
	}
}
