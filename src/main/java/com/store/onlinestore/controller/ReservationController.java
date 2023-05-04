package com.store.onlinestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@PutMapping(path = "/{userId}")
	public String updateReservation(@RequestBody ReservationDto reservationDto)  {
		return reservationService.update(reservationDto);
	}
	
	@DeleteMapping(path = "/admin/{id}")
	public String delete(@PathVariable Long id)  {
		return reservationService.delete(id);
	}
	
	@GetMapping
	public List<Reservation> getNotOccupiedReservations() {
		return reservationService.getNotOccupiedReservations();
	}
	
	@GetMapping("/{userId}")
	public List<Reservation> getAllReservationsOfUser(@PathVariable Long userId) {
		return reservationService.getAllReservationsOfUser(userId);
	}
}
