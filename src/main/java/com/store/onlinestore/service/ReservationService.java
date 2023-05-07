package com.store.onlinestore.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.store.onlinestore.dto.ReservationDto;
import com.store.onlinestore.entity.Reservation;

public interface ReservationService {

	void save(ReservationDto reservationDto);
	
	String delete(Long id, Authentication authentication);

	List<Reservation> getNotOccupiedReservations();

	List<Reservation> getAllReservationsOfUser(Authentication authentication);

	String updateUserToReservation(Long id, Authentication authentication);

	String deleteUserFromReservation(Long id, Authentication authentication);
}
