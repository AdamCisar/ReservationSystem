package com.store.onlinestore.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDtoResponse {
	
	private Long id;
	@JsonProperty("date")
    private LocalDate date;
	@JsonProperty("time")
    private LocalTime time;
}
