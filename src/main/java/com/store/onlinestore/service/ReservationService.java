package com.store.onlinestore.service;

import com.store.onlinestore.dto.ReservationDto;

public interface ReservationService {

	String save(ReservationDto reservationDto);
	
	String update(ReservationDto reservationDto);
}
