package com.Pulson.RoomReservations.controllers;

import com.Pulson.RoomReservations.entities.Reservation;
import com.Pulson.RoomReservations.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/all")
    public List<Reservation> getAll(){
        return reservationService.getAll();
    }

    @GetMapping("/my")
    public List<Reservation> getUsersReservations(@PathVariable("id") long id) throws Exception {
        return reservationService.getByUser(id);
    }

    @GetMapping("/{id}")
    public Reservation getById(@PathVariable("id") long id) throws Exception {
        return reservationService.getById(id);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean create(@RequestBody Reservation reservation) throws Exception {
        return reservationService.create(reservation);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") long id) throws Exception {
        return reservationService.delete(id);
    }

    @PutMapping("/update/{id}")
    public boolean update(@PathVariable("id") long id, @RequestBody Reservation reservationDetails) throws Exception {
        //return reservationService.update(id, reservationDetails);
        return false;
    }
}
