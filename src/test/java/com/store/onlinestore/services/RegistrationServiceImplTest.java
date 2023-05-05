package com.store.onlinestore.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.store.onlinestore.dto.UserDto;
import com.store.onlinestore.entity.User;
import com.store.onlinestore.repository.RoleRepository;
import com.store.onlinestore.repository.UserRepository;
import com.store.onlinestore.service.impl.RegistrationServiceImpl;

@ExtendWith(MockitoExtension.class)
public class RegistrationServiceImplTest {

	@Mock
	private RoleRepository roleRepository;
	@Mock
	private PasswordEncoder passwordEncoder;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private RegistrationServiceImpl registrationServiceImpl;
    
	@Test
	void shouldRegisterUser() {
		//given  	
		UserDto userDto = new UserDto("firstNameTest", "lastNameTest", "test@test.com", "password123");
		//when
        registrationServiceImpl.register(userDto);
        //then
        ArgumentCaptor<User> argCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(argCaptor.capture());
        
        User capturedUser = argCaptor.getValue();
        
        assertEquals(userDto.getFirstName(), capturedUser.getFirstName());
        assertEquals(userDto.getLastName(), capturedUser.getLastName());
        assertEquals(userDto.getEmail(), capturedUser.getEmail());
        
	}
}
