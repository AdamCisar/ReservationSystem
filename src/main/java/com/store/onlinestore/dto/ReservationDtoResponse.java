package com.store.onlinestore.dto;

import java.sql.Date;
import java.sql.Time;

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
    private Date date;
	@JsonProperty("time")
    private Time time;
}
