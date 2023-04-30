package com.store.onlinestore.services;

import org.springframework.stereotype.Service;

import com.store.onlinestore.dto.UserDto;
import com.store.onlinestore.entities.User;
import com.store.onlinestore.entities.UserRole;
import com.store.onlinestore.repositories.UserRepository;

@Service
public class RegistrationService {

	private UserRepository userRepository;
	
	public RegistrationService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public String register(UserDto userDto) {
		User user = new User(
				userDto.getName(),
				userDto.getEmail(),
				userDto.getPassword(),
				UserRole.USER
				);
		userRepository.save(user);
	return "success";
	}
}
