package com.store.onlinestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.onlinestore.dto.ReservationDto;
import com.store.onlinestore.entity.Reservation;
import com.store.onlinestore.service.ReservationService;

@RequestMapping("/api/reservation")
@RestController
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	
	@PostMapping("/admin")
	public String uplodReservation(@RequestBody ReservationDto reservationDto) {
		return reservationService.save(reservationDto);
	}
	
	@PutMapping(path = "/{id}")
	public String updateReservation(@PathVariable Long id, Authentication authentication)  {
		return reservationService.update(id, authentication);
		}
	
	@DeleteMapping(path = "/{id}")
	public String delete(@PathVariable Long id, Authentication authentication)  {
		return reservationService.delete(id, authentication);
	}
	
	@GetMapping
	public List<Reservation> getNotOccupiedReservations() {
		return reservationService.getNotOccupiedReservations();
	}
	
	@GetMapping("/userReservations")
	public List<Reservation> getAllReservationsOfUser(Authentication authentication) {
		return reservationService.getAllReservationsOfUser(authentication);
	}
}
