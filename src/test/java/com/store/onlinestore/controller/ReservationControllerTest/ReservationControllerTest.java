package com.store.onlinestore.controller.ReservationControllerTest;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.onlinestore.config.WebSecurityConfig;
import com.store.onlinestore.controller.ReservationController;
import com.store.onlinestore.service.impl.ReservationServiceImpl;

@WebMvcTest(ReservationController.class)
@Import(WebSecurityConfig.class)
public class ReservationControllerTest {
	
    @MockBean
    private ReservationServiceImpl reservationServiceImpl;
    
    @Autowired
	protected ObjectMapper objectMapper;
    
    @Autowired
	protected MockMvc mockMvc;
    
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @BeforeEach()
    public void setup()
    {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
        		.apply(springSecurity())
        		.build();
    }
}
