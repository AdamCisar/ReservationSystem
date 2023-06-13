package com.store.onlinestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.onlinestore.dto.LoginDto;
import com.store.onlinestore.dto.UserDto;
import com.store.onlinestore.service.RegistrationService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
@RestController
public class AuthController {
	
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
	private RegistrationService registrationService;
	
	@PostMapping(path = "/register")
	public ResponseEntity<String> register(@Valid @RequestBody UserDto userDto) {
		registrationService.register(userDto);
		return new ResponseEntity<>("User registration successful!", HttpStatus.CREATED);
	}
	
    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }
	
}
