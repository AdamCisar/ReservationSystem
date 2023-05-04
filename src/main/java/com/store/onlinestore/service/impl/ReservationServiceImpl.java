package com.store.onlinestore.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.onlinestore.dto.ReservationDto;
import com.store.onlinestore.entity.Reservation;
import com.store.onlinestore.entity.User;
import com.store.onlinestore.repository.ReservationRepository;
import com.store.onlinestore.repository.UserRepository;
import com.store.onlinestore.service.ReservationService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReservationServiceImpl implements ReservationService{

	@Autowired
	private ReservationRepository reservationRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public String save(ReservationDto reservationDto) {
		Reservation reservation = new Reservation();
		
		reservation.setReservationDate(reservationDto.getReservationDate());
		reservation.setReservationTime(reservationDto.getReservationTime());
		
		reservationRepository.save(reservation);
		return "reservation created";
	}

	@Override
	public String update(ReservationDto reservationDto) {
		Reservation reservation = reservationRepository.findById(reservationDto.getReservationId()).orElseThrow(() -> new EntityNotFoundException("Reservation not found"));
		User user = userRepository.findById(reservationDto.getUserId()).orElseThrow(() -> new EntityNotFoundException("Reservation not found"));

		reservation.setUser(user);
		reservationRepository.save(reservation);
		
		return "reservation updated";
	}

	@Override
	public String delete(Long id) {
		Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Reservation not found"));
		reservationRepository.delete(reservation);
		return "reservation deleted";
	}

	@Override
	public List<Reservation> getNotOccupiedReservations() {
		
		return reservationRepository.findAll().stream().filter(reservation -> reservation.getUser() == null).collect(Collectors.toList());
	}

	@Override
	public List<Reservation> getAllReservationsOfUser(Long userId) {

		return reservationRepository.findByUserId(userId); 
	}
	


}
