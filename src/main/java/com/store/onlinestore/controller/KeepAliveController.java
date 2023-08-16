package com.store.onlinestore.controller;

import com.store.onlinestore.dto.ReservationDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(
        origins =
                "https://cute-cobbler-7a8154.netlify.app",
        allowCredentials = "true"
)
@RequestMapping("/api/keepalive")
@RestController
public class KeepAliveController {
    @GetMapping
    public List<ReservationDtoResponse> getNotOccupiedReservations() {
        List<ReservationDtoResponse> o = null;
        return o;
    }
}
