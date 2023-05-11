package com.store.onlinestore.controller.ReservationControllerTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.sql.Time;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;

import com.store.onlinestore.dto.ReservationDto;

public class AdminApiTest extends ReservationControllerTest{

    @Test
    void shouldReturns403IfUnauthenticatedWhenPosting() throws Exception {
    	ReservationDto reservationDto = new ReservationDto();
   	    reservationDto.setReservationDate(new Date(1));
   	    reservationDto.setReservationTime(new Time(1));
   	    
    	mockMvc.perform(post("/api/reservation/admin")
    			.with(csrf())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(reservationDto)))
                .andExpect(status().is3xxRedirection());
    }
    
    @Test
    void shouldReturns403IfUnauthenticatedWhenDeleting() throws Exception {
    	mockMvc.perform(delete("/api/reservation/admin/{id}",1L)
    			.with(csrf()))
                .andExpect(status().is3xxRedirection());
    }
    

    @Test
    @WithMockUser
    void shouldReturns403IfUserPosting() throws Exception {
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
    void shouldReturns403IfUserDeleting() throws Exception {
    	mockMvc.perform(delete("/api/reservation/admin/{id}", 1L)
    			.with(csrf()))
                .andExpect(status().isForbidden());
    }
    
    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturns201IfAdminPosting_And_MapsToServiceLayer_And_ReturnsResponse() throws Exception {
    	ReservationDto reservationDto = new ReservationDto();
   	    reservationDto.setReservationDate(new Date(1));
   	    reservationDto.setReservationTime(new Time(1));
   	    
   	    //201 status code
   	    MvcResult mvcResult = mockMvc.perform(post("/api/reservation/admin")
    			.with(csrf())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(reservationDto)))
                .andExpect(status().isCreated())
                .andReturn();
    	
    	 //Maping to service
    	 ArgumentCaptor<ReservationDto> reservationCaptor = ArgumentCaptor.forClass(ReservationDto.class);
         verify(reservationServiceImpl).save(reservationCaptor.capture());
         
        
         ReservationDto capturedReservation = reservationCaptor.getValue();
         assertThat(reservationCaptor.getValue()).isEqualTo(capturedReservation);
        
         //Response
         String actualResponseBody = mvcResult.getResponse().getContentAsString();
     	 assertThat(actualResponseBody).isEqualTo("Reservation has been created!");
    }
    
    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturns200IfAdminDeleting_And_MapsToServiceLayer_And_ReturnsResponse() throws Exception {
    	//status code 200
    	MvcResult mvcResult = mockMvc.perform(delete("/api/reservation/admin/{id}",1L)
    			.with(csrf()))
                .andExpect(status().isOk())
                .andReturn();
    	
    	 //Maping to service
   	 	ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
        verify(reservationServiceImpl).delete(idCaptor.capture());
        
       
        Long capturedId = idCaptor.getValue();
        
        assertThat(idCaptor.getValue()).isEqualTo(capturedId);
       
        //Response
        String actualResponseBody = mvcResult.getResponse().getContentAsString();
    	 assertThat(actualResponseBody).isEqualTo("Reservation has been deleted!");
    }
}
