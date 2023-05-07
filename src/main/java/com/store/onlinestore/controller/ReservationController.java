package com.store.onlinestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.onlinestore.dto.ReservationDto;
import com.store.onlinestore.entity.Reservation;
import com.store.onlinestore.service.ReservationService;

import jakarta.validation.Valid;

@RequestMapping("/api/reservation")
@RestController
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	
	@PostMapping("/admin")
	public ResponseEntity<String> uplodReservation(@Valid @RequestBody ReservationDto reservationDto) {
		reservationService.save(reservationDto);
		return new ResponseEntity<>("Reservation has been created!", HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "/admin/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id, Authentication authentication)  {
		reservationService.delete(id, authentication);
		return new ResponseEntity<>("Reservation has been deleted!", HttpStatus.OK);
	}
	
	@PatchMapping(path = "/update-user/{id}")
	public String updateUserToReservation(@PathVariable Long id, Authentication authentication)  {
		return reservationService.updateUserToReservation(id, authentication);
	}
	
	@PatchMapping(path = "/delete-user/{id}")
	public String deleteUserFromReservation(@PathVariable Long id, Authentication authentication)  {
		return reservationService.deleteUserFromReservation(id, authentication);
	}
	
	@GetMapping
	public List<Reservation> getNotOccupiedReservations() {
		return reservationService.getNotOccupiedReservations();
	}
	
	@GetMapping("/user-reservations")
	public List<Reservation> getAllReservationsOfUser(Authentication authentication) {
		return reservationService.getAllReservationsOfUser(authentication);
	}
}
