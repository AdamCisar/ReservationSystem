package com.store.onlinestore.controller.ReservationControllerTest;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.sql.Date;
import java.sql.Time;

import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import com.store.onlinestore.dto.ReservationDto;

public class AdminApiTest extends ReservationControllerTest{


    @Test
    @WithMockUser
    void shouldReturns403IfUnauthenticatedWhenPosting() throws Exception {
    	ReservationDto reservationDto = new ReservationDto();
   	    reservationDto.setReservationDate(new Date(1));
   	    reservationDto.setReservationTime(new Time(1));
   	    
    	mockMvc.perform(post("/api/reservation/admin")
    			.with(csrf())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(reservationDto)))
                .andExpect(status().isForbidden());
    }
    
    @Test
    @WithMockUser
    void shouldReturns403IfUnauthenticatedWhenDeleting() throws Exception {
    	mockMvc.perform(delete("/api/reservation/admin/1")
    			.with(csrf()))
                .andExpect(status().isForbidden());
    }
    
    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturns201IfAdminPosting() throws Exception {
    	ReservationDto reservationDto = new ReservationDto();
   	    reservationDto.setReservationDate(new Date(1));
   	    reservationDto.setReservationTime(new Time(1));
   	    
    	mockMvc.perform(post("/api/reservation/admin")
    			.with(csrf())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(reservationDto)))
                .andExpect(status().isCreated());
    }
    
    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturns200IfAdminDeleting() throws Exception {
    	mockMvc.perform(delete("/api/reservation/admin/1")
    			.with(csrf()))
                .andExpect(status().isOk());
    }
}
