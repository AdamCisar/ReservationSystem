package com.store.onlinestore.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.store.onlinestore.dto.ReservationDto;
import com.store.onlinestore.entity.Reservation;

public interface ReservationService {

	String save(ReservationDto reservationDto);
	
	String update(Long id, Authentication authentication);

	String delete(Long id, Authentication authentication);

	List<Reservation> getNotOccupiedReservations();

	List<Reservation> getAllReservationsOfUser(Authentication authentication);
}
