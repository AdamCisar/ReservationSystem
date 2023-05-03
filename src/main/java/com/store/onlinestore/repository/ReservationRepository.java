package com.store.onlinestore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.onlinestore.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{

	Optional<Reservation> findById(Long id);

	void save(Optional<Reservation> reservation);
}
