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
public class ReservationDto {
	
	@NotNull
    private Date reservationDate;
	@NotNull
    private Time reservationTime;
}
