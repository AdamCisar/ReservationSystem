package com.store.onlinestore.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutomaticReservationCreate {
		
	@NotNull
    private LocalDate reservationFrom;
	@NotNull
    private LocalDate reservationTo;
}
