package com.store.onlinestore.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.store.onlinestore.dto.UserDto;
import com.store.onlinestore.services.RegistrationService;

@RestController
public class RegistrationController {

	private RegistrationService registrationService;
	
	public RegistrationController(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}
	
	@PostMapping(path = "/register")
	public String register(@RequestBody UserDto userDto) {
		return registrationService.register(userDto);
		
	}
}
