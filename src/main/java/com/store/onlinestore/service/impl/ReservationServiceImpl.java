package com.store.onlinestore.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
	public void save(ReservationDto reservationDto) {
		Reservation reservation = new Reservation();
		
		reservation.setReservationDate(reservationDto.getReservationDate());
		reservation.setReservationTime(reservationDto.getReservationTime());
		
		reservationRepository.save(reservation);
	}

	@Override
	public String updateUserToReservation(Long id, Authentication authentication) {
		Reservation reservation = getReservationById(id);

		reservation.setUser(getCurrentUser(authentication));
		reservationRepository.save(reservation);
		
		return "reservation updated";
	}
	
	@Override
	public String deleteUserFromReservation(Long id, Authentication authentication) {
		//GET CURRENT USER AND RESERVATION FROM DATABASE 
		User currentUser = getCurrentUser(authentication);
		Reservation reservation = getReservationById(id);
		var userReservationId = reservation.getUser();
		//VALIDATE
		if(!currentUser.equals(userReservationId) || userReservationId == null) {
			throw new AccessDeniedException("You are not allowed to delete this reservation!");
		}
		reservation.setUser(null);
		reservationRepository.save(reservation);

		return "user is deleted from reservation";
	}
	
	@Override
	public String delete(Long id, Authentication authentication) {
		//GET CURRENT USER AND RESERVATION FROM DATABASE 
		User currentUser = getCurrentUser(authentication);
		Reservation reservation = getReservationById(id);
		
		//CHECK IF CURRENT USER IS ADMIN
		boolean isAdmin = currentUser.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_ADMIN"));
		
		//VALIDATE
		var userReservationId = reservation.getUser();
		
		if(isAdmin) {
			reservationRepository.delete(reservation);
			return "reservation deleted";
		}
		
		if(!currentUser.equals(userReservationId) || userReservationId == null) {
			throw new AccessDeniedException("You are not allowed to delete this reservation!");
		}
		
		reservationRepository.delete(reservation);

		return "reservation deleted";
	}

	@Override
	public List<Reservation> getNotOccupiedReservations() {
		
		return reservationRepository.findAll().stream().filter(reservation -> reservation.getUser() == null).collect(Collectors.toList());
	}

	@Override
	public List<Reservation> getAllReservationsOfUser(Authentication authentication) {

		return reservationRepository.findByUserId(getCurrentUser(authentication).getId()); 
	}
	
	private Reservation getReservationById(Long id) {
		
		return reservationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Reservation not found"));
	}
	
	private User getCurrentUser(Authentication authentication) {
		 
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		 return userRepository.findByEmail(userDetails.getUsername());
	}


}
