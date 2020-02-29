package com.Pulson.RoomReservations.controllers;

import com.Pulson.RoomReservations.entities.Reservation;
import com.Pulson.RoomReservations.entities.dtos.ReservationAddDTO;
import com.Pulson.RoomReservations.entities.dtos.ReservationReadDTO;
import com.Pulson.RoomReservations.services.ReservationService;
import com.Pulson.RoomReservations.services.mappers.CreateReservationMapper;
import com.Pulson.RoomReservations.services.mappers.ReadReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private CreateReservationMapper createReservationMapper;

    @Autowired
    private ReadReservationMapper readReservationMapper;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReservationReadDTO> getAll(){
        return readReservationMapper.mapToReservationReadDTO(reservationService.getAll());
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
    public boolean create(@RequestBody ReservationAddDTO reservationAddDTO) throws Exception {
        return reservationService.create(createReservationMapper.mapToReservation(reservationAddDTO));
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
