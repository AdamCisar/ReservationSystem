package com.store.onlinestore.services;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.store.onlinestore.dto.UserDto;
import com.store.onlinestore.entities.User;
import com.store.onlinestore.repositories.RoleRepository;
import com.store.onlinestore.repositories.UserRepository;

@Service
public class RegistrationService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	
	public RegistrationService(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	public String register(UserDto userDto) {
		User user = new User(
				userDto.getFirstName(),
				userDto.getLastName(),
				userDto.getEmail(),
				userDto.getPassword(),
				Arrays.asList(roleRepository.findByName("ROLE_USER"))
				);
		userRepository.save(user);
	return "success";
	}
}
