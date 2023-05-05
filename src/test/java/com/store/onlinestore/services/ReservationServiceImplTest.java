package com.store.onlinestore.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.sql.Date;
import java.sql.Time;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.store.onlinestore.dto.ReservationDto;
import com.store.onlinestore.entity.Reservation;
import com.store.onlinestore.repository.ReservationRepository;
import com.store.onlinestore.repository.UserRepository;
import com.store.onlinestore.service.impl.ReservationServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceImplTest {

	
	@Mock
	private ReservationRepository reservationRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private ReservationServiceImpl reservationServiceImpl;
    
	@Test
	void shouldSaveReservationToRepository() {
	    //given
	    ReservationDto reservationDto = new ReservationDto();
	    reservationDto.setReservationDate(new Date(1));
	    reservationDto.setReservationTime(new Time(1));
	    //when
	    String result = reservationServiceImpl.save(reservationDto);
	    
	    //then
	    ArgumentCaptor<Reservation> argCaptor = ArgumentCaptor.forClass(Reservation.class);
	    verify(reservationRepository).save(argCaptor.capture());
	    
	    Reservation capturedReservation = argCaptor.getValue();
	    
	    assertEquals(reservationDto.getReservationDate(), capturedReservation.getReservationDate());
	    assertEquals(reservationDto.getReservationTime(), capturedReservation.getReservationTime());
	    assertEquals("reservation created", result);
        
	}
}
