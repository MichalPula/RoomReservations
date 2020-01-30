package com.Pulson.RoomReservations.controller;

import com.Pulson.RoomReservations.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reservations")
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;
}