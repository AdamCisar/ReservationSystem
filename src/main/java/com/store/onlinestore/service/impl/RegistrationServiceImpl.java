package com.store.onlinestore.service.impl;

import java.util.Arrays;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.store.onlinestore.dto.UserDto;
import com.store.onlinestore.entity.User;
import com.store.onlinestore.repository.RoleRepository;
import com.store.onlinestore.repository.UserRepository;
import com.store.onlinestore.service.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	
	public RegistrationServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public void register(UserDto userDto) {
		
		User user = new User(
				userDto.getFirstName(),
				userDto.getLastName(),
				userDto.getEmail(),
				passwordEncoder.encode(userDto.getPassword()),
				true,
				Arrays.asList(roleRepository.findByName("ROLE_USER"))
				);
		userRepository.save(user);
	}
}
