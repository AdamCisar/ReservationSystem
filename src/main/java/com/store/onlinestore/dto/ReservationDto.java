package com.store.onlinestore.dto;

import java.sql.Date;
import java.sql.Time;

import com.store.onlinestore.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {
	
	private Long userId;
	private Long reservationId;
    private Date reservationDate;
    private Time reservationTime;
}
