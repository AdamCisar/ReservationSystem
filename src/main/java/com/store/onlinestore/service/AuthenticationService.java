package com.store.onlinestore.service;

import com.store.onlinestore.dto.AuthenticationResponse;
import com.store.onlinestore.dto.LoginDto;
import com.store.onlinestore.dto.UserDto;

public interface AuthenticationService {
	
	public AuthenticationResponse register(UserDto userDto);

	public AuthenticationResponse authenticate(LoginDto loginDto);
}
