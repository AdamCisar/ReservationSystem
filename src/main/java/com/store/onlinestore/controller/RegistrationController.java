package com.store.onlinestore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.onlinestore.dto.UserDto;
import com.store.onlinestore.service.RegistrationService;

import jakarta.validation.Valid;

@RequestMapping("/api")
@RestController
public class RegistrationController {

	private RegistrationService registrationService;
	
	public RegistrationController(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}
	
	@PostMapping(path = "/register")
	public ResponseEntity<String> register(@Valid @RequestBody UserDto userDto) {
		registrationService.register(userDto);
		return new ResponseEntity<>("User registration successful!", HttpStatus.CREATED);
	}
	
}
