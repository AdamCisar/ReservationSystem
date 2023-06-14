package com.store.onlinestore.service.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.store.onlinestore.dto.AuthenticationResponse;
import com.store.onlinestore.dto.LoginDto;
import com.store.onlinestore.dto.UserDto;
import com.store.onlinestore.entity.Token;
import com.store.onlinestore.entity.User;
import com.store.onlinestore.repository.RoleRepository;
import com.store.onlinestore.repository.TokenRepository;
import com.store.onlinestore.repository.UserRepository;
import com.store.onlinestore.security.TokenService;
import com.store.onlinestore.service.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private TokenRepository tokenRepository;

	@Override
	public AuthenticationResponse register(UserDto userDto) {
		var user = User.builder()
				.firstName(userDto.getFirstName())
				.lastName(userDto.getLastName())
				.email(userDto.getEmail())
				.password(passwordEncoder.encode(userDto.getPassword()))
				.enabled(true)
				.roles(Arrays.asList(roleRepository.findByName("ROLE_USER")))
				.build();
		
		var savedUser = userRepository.save(user);
		var jwtToken = tokenService.generateToken(user);
		saveUserToken(savedUser, jwtToken);
		return AuthenticationResponse.builder()
		        .accessToken(jwtToken)
		        .build();
			
	}
	
	@Override
	public AuthenticationResponse authenticate(LoginDto loginDto) {
	    authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(
        		loginDto.getEmail(),
        		loginDto.getPassword()
	        )
	    );
	    var user = userRepository.findByEmail(loginDto.getEmail());
	    var jwtToken = tokenService.generateToken(user);
	    
	    return AuthenticationResponse.builder()
	        .accessToken(jwtToken)
	        .build();
	  }
	
	 private void saveUserToken(User user, String jwtToken) {
		    var token = Token.builder()
		        .user(user)
		        .token(jwtToken)
		        .tokenType("BEARER")
		        .expired(false)
		        .revoked(false)
		        .build();
		    tokenRepository.save(token);
		  }
	
}
