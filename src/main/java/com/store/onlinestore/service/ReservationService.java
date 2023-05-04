package com.store.onlinestore.service;

import java.util.List;

import com.store.onlinestore.dto.ReservationDto;
import com.store.onlinestore.entity.Reservation;

public interface ReservationService {

	String save(ReservationDto reservationDto);
	
	String update(ReservationDto reservationDto);

	String delete(Long id);

	List<Reservation> getNotOccupiedReservations();

	List<Reservation> getAllReservationsOfUser(Long userId);
}
