package com.store.onlinestore.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.store.onlinestore.dto.AuthenticationResponse;
import com.store.onlinestore.dto.LoginDto;
import com.store.onlinestore.dto.UserDto;
import com.store.onlinestore.entity.Role;
import com.store.onlinestore.entity.User;
import com.store.onlinestore.repository.RoleRepository;
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

	@Override
	public AuthenticationResponse register(UserDto userDto) {
		Collection<Role> roles = Arrays.asList(roleRepository.findByName("ROLE_USER"));
		
		var user = User.builder()
				.firstName(userDto.getFirstName())
				.lastName(userDto.getLastName())
				.email(userDto.getEmail())
				.password(passwordEncoder.encode(userDto.getPassword()))
				.enabled(true)
				.roles(roles)
				.build();
		
		userRepository.save(user);
		var jwtToken = tokenService.generateToken(user, getRole(roles), user.getId().toString());
		return AuthenticationResponse.builder()
		        .token(jwtToken)
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
	    var jwtToken = tokenService.generateToken(user, getRole(user.getRoles()), user.getId().toString());
	    return AuthenticationResponse.builder()
	        .token(jwtToken)
	        .build();
	  }
	
	 private Map<String, ArrayList<String>> getRole(Collection<Role> roles) {
		 ArrayList<String> newRoles = new ArrayList<>(roles.size());

        for (Role role : roles) {
            newRoles.add(role.getName());
        }
		 
		 Map<String, ArrayList<String>> claims = new HashMap<>();
		 claims.put("roles", newRoles);
		 return claims;
	 }
	
}
