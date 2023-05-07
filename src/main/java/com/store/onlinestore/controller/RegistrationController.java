package com.store.onlinestore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.store.onlinestore.dto.UserDto;
import com.store.onlinestore.service.RegistrationService;

@RequestMapping("/api")
@RestController
public class RegistrationController {

	private RegistrationService registrationService;
	
	public RegistrationController(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}
	
	@PostMapping(path = "/register")
	@ResponseStatus(HttpStatus.CREATED)
	public String register(@RequestBody UserDto userDto) {
		return registrationService.register(userDto);
	}
	
}
