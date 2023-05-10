package com.store.onlinestore.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import com.store.onlinestore.dto.ReservationDto;
import com.store.onlinestore.entity.Reservation;
import com.store.onlinestore.entity.User;
import com.store.onlinestore.repository.ReservationRepository;
import com.store.onlinestore.repository.UserRepository;
import com.store.onlinestore.service.impl.ReservationServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceImplTest {

	@Mock
    private Authentication authentication;
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
	    reservationServiceImpl.save(reservationDto);
	    
	    //then
	    ArgumentCaptor<Reservation> argCaptor = ArgumentCaptor.forClass(Reservation.class);
	    verify(reservationRepository).save(argCaptor.capture());
	    
	    Reservation capturedReservation = argCaptor.getValue();
	    
	    assertEquals(reservationDto.getReservationDate(), capturedReservation.getReservationDate());
	    assertEquals(reservationDto.getReservationTime(), capturedReservation.getReservationTime());
        
	}
	
	@Test
	void shouldDeleteReservation() {
		Long id = 1L;
		Reservation reservation = new Reservation();
		reservation.setId(id);

		when(reservationRepository.findById(id)).thenReturn(Optional.of(reservation));

		reservationServiceImpl.delete(id);

		ArgumentCaptor<Reservation> captor = ArgumentCaptor.forClass(Reservation.class);
	    verify(reservationRepository).delete(captor.capture());
	    assertEquals(reservation, captor.getValue());
	}
	
	@Test
	void shouldUpdateUserToReservation() {
		User user = new User();
		user.setId(1L);
		Reservation reservation = new Reservation();
		reservation.setId(1L);
		
	    when(reservationRepository.findById(reservation.getId())).thenReturn(Optional.of(reservation));
	    when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

	    reservationServiceImpl.updateUserToReservation(reservation.getId(), user.getId());

	    verify(reservationRepository).save(reservation);
	    assertEquals(user, reservation.getUser());
	}
}
