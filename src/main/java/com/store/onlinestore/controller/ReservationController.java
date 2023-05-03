package com.store.onlinestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.onlinestore.dto.ReservationDto;
import com.store.onlinestore.service.ReservationService;

@RequestMapping("/api")
@RestController
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	
	@PostMapping(path = "/reservation")
	public String uplodReservation(@RequestBody ReservationDto reservationDto) {
		return reservationService.save(reservationDto);
	}
	
	@PostMapping(path = "/reservation/addUser")
	public String updateReservation(@RequestBody ReservationDto reservationDto)  {
		return reservationService.update(reservationDto);
	}
}
