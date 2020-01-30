package com.Pulson.RoomReservations.controller;

import com.Pulson.RoomReservations.model.Reservation;
import com.Pulson.RoomReservations.model.Room;
import com.Pulson.RoomReservations.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("reservations")
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping("/all")
    public List<Reservation> getAll(){
        return reservationRepository.findAll();
    }
}