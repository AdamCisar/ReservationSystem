package com.store.onlinestore.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.store.onlinestore.dto.ReservationDto;
import com.store.onlinestore.entity.Reservation;
import com.store.onlinestore.entity.User;
import com.store.onlinestore.repository.ReservationRepository;
import com.store.onlinestore.repository.UserRepository;
import com.store.onlinestore.service.ReservationService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReservationServiceImpl implements ReservationService {

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
	public void delete(Long id) {
		Reservation reservation = getReservationById(id);
		reservationRepository.delete(reservation);
	}

	@Override
	public void updateUserToReservation(Long reservationId, Long userId) {
		User currentUser = getCurrentUser();
		// GET USER AND RESERVATION FROM DATABASE
		Reservation reservation = getReservationById(reservationId);
		User user = getUser(userId);
		// VALIDATE
		if (!user.getId().equals(currentUser.getId())) {
			throw new AccessDeniedException("You cannot assign another user to the reservation!");
		}
		reservation.setUser(user);
		reservationRepository.save(reservation);
	}

	@Override
	public void deleteUserFromReservation(Long reservationId, Long userId) {
		// GET CURRENT USER
		User currentUser = getCurrentUser();
		// GET USER AND RESERVATION FROM DATABASE
		User user = getUser(userId);
		Reservation reservation = getReservationById(reservationId);
		var userReservation = reservation.getUser();
		// VALIDATE
		if (!user.getId().equals(userReservation.getId()) && !user.getId().equals(currentUser.getId())) {
			throw new AccessDeniedException("You are not allowed to delete this reservation!");
		}
		reservation.setUser(null);
		reservationRepository.save(reservation);
	}

	@Override
	public List<Reservation> getNotOccupiedReservations() {

		return reservationRepository.findAll().stream().filter(reservation -> reservation.getUser() == null)
				.collect(Collectors.toList());
		
	}

	@Override
	public List<Reservation> getAllReservationsOfUser(Long userId) {
		User currentUser = getCurrentUser();
		// GET USER AND RESERVATION FROM DATABASE
		User user = getUser(userId);
		// VALIDATE
		if (!user.getId().equals(currentUser.getId())) {
			throw new AccessDeniedException("You cannot see reservations of another users!");
		}
		return reservationRepository.findByUserId(userId);
	}

	private Reservation getReservationById(Long id) {

		return reservationRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Reservation not found"));
	}

	private User getUser(Long userId) {
		return userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
	}

	public User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String userEmail = authentication.getName();
		return userRepository.findByEmail(userEmail);
	}

}
