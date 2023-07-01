package com.store.onlinestore.service;

import java.util.List;

import com.store.onlinestore.dto.AutomaticReservationCreate;
import com.store.onlinestore.dto.CreateReservation;
import com.store.onlinestore.dto.ReservationDto;
import com.store.onlinestore.dto.ReservationDtoResponse;
import com.store.onlinestore.entity.Reservation;

import jakarta.validation.Valid;

public interface ReservationService {

	void save(ReservationDto reservationDto);
	
	void delete(Long id);

	List<ReservationDtoResponse> getNotOccupiedReservations();

	List<Reservation> getAllReservationsOfUser(Long userId);

	void updateUserToReservation(Long id, Long userId);

	void deleteUserFromReservation(Long id, Long userId);

	void createReservation(@Valid AutomaticReservationCreate automaticReservationCreate);
}
