//package com.store.onlinestore.services;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.verify;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import com.store.onlinestore.dto.UserDto;
//import com.store.onlinestore.entities.User;
//import com.store.onlinestore.entities.UserRole;
//import com.store.onlinestore.repositories.UserRepository;
//
//@ExtendWith(MockitoExtension.class)
//public class RegistrationServiceTest {
//
//
//    private RegistrationService registrationService;
//
//    @Mock
//    private UserRepository userRepository;
//    
//    @BeforeEach
//    public void setUp() {
//        registrationService = new RegistrationService(userRepository);
//    }
//
//    
//	@Test
//	void shouldRegisterStudent() {
//		//given  	
//		UserDto userDto = new UserDto("Test User", "test@test.com", "password123");
//		//when
//        registrationService.register(userDto);
//        //then
//        ArgumentCaptor<User> argCaptor = ArgumentCaptor.forClass(User.class);
//        verify(userRepository).save(argCaptor.capture());
//        
//        User capturedUser = argCaptor.getValue();
//        
//        assertEquals(userDto.getName(), capturedUser.getName());
//        assertEquals(userDto.getEmail(), capturedUser.getEmail());
//        assertEquals(userDto.getPassword(), capturedUser.getPassword());
//        assertEquals(UserRole.USER, capturedUser.getUserRole());
//        
//	}
//}
