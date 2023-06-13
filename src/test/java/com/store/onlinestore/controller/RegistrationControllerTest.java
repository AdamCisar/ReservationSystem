package com.store.onlinestore.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.onlinestore.dto.UserDto;
import com.store.onlinestore.service.impl.RegistrationServiceImpl;


@WebMvcTest(AuthController.class)
public class RegistrationControllerTest {
	
    @MockBean
    private RegistrationServiceImpl registrationServiceImpl;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach()
    public void setup()
    {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
    @Test 
    void shouldReturns201() throws Exception {
    	UserDto userDto = new UserDto("Test", "lastNameTest", "test@test.com", "test");
    	
    	mockMvc.perform(post("/api/register")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated());
    }
    
    @Test 
    void shouldReturns400WhenNullValueOrBlank() throws Exception {
    	UserDto userDto = new UserDto(null,"lastNameTest", "test@test.com", "test");
    	
    	mockMvc.perform(post("/api/register")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    void whenValidInput_thenMapsToServiceLayer() throws Exception {
      UserDto userDto = new UserDto("Test","lastNameTest", "test@test.com", "test");

      mockMvc.perform(post("/api/register")
              .contentType("application/json")
              .content(objectMapper.writeValueAsString(userDto)))
              .andExpect(status().isCreated());

      ArgumentCaptor<UserDto> userCaptor = ArgumentCaptor.forClass(UserDto.class);
      verify(registrationServiceImpl, times(1)).register(userCaptor.capture());
      
      UserDto capturedUser = userCaptor.getValue();
      
      assertThat(userCaptor.getValue()).isEqualTo(capturedUser);
    }
    
    @Test
    void whenValidInput_thenReturnsResponse() throws Exception {
    	UserDto userDto = new UserDto("Test","lastNameTest", "test@test.com", "test");
    	
    	MvcResult mvcResult = mockMvc.perform(post("/api/register")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userDto)))
    			.andReturn();
    	
    	String actualResponseBody = mvcResult.getResponse().getContentAsString();
    	assertThat(actualResponseBody).isEqualTo("User registration successful!");
    }
    
    
   
}
