package com.store.onlinestore.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.store.onlinestore.services.RegistrationService;

@RestController
public class RegistrationController {

	private RegistrationService registrationService;
	
	public RegistrationController(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}
	
	@GetMapping(path = "/register")
	public ModelAndView register(Model model) {
//		return registrationService.register(userDto);
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("view.html");
        return modelAndView;
	}
	
	@GetMapping(path = "/admin")
	public String admin() {
		return "only admin";
		
	}
	
	@GetMapping(path = "/user")
	public String user() {
		return "users";
		
	}
}
