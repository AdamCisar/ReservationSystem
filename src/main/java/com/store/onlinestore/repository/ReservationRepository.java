package com.store.onlinestore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.store.onlinestore.entity.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{

	Optional<Reservation> findById(Long id);

	void save(Optional<Reservation> reservation);
	
	@Query("SELECT r FROM Reservation r JOIN r.user u WHERE u.id = :userId")
	List<Reservation> findByUserId(@Param("userId") Long userId);
}
