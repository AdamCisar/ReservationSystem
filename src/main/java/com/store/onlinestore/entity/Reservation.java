package com.store.onlinestore.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate reservationDate;
    private LocalTime reservationTime;

	@ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    public Reservation(LocalDate reservationDate, LocalTime reservationTime, User user) {
		this.reservationDate = reservationDate;
		this.reservationTime = reservationTime;
		this.user = user;
	}

	public Reservation(LocalDate reservationDate, LocalTime reservationTime) {
		this.reservationDate = reservationDate;
		this.reservationTime = reservationTime;
	}
	    
}