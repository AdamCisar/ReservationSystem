package com.store.onlinestore.dto;

import java.sql.Date;
import java.sql.Time;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDtoResponse {
	
    private String reservationDate;
    private Time reservationTime;
}
