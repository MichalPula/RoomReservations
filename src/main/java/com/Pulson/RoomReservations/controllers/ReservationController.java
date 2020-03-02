package com.Pulson.RoomReservations.controllers;

import com.Pulson.RoomReservations.entities.dtos.ReservationCreateUpdateDTO;
import com.Pulson.RoomReservations.entities.dtos.ReservationReadDTO;
import com.Pulson.RoomReservations.services.ReservationService;
import com.Pulson.RoomReservations.services.mappers.CreateUpdateReservationMapper;
import com.Pulson.RoomReservations.services.mappers.ReadReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private CreateUpdateReservationMapper createUpdateReservationMapper;

    @Autowired
    private ReadReservationMapper readReservationMapper;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReservationReadDTO> getAll(){
        return readReservationMapper.mapToReservationReadDTOsList(reservationService.getAll());
    }

    @GetMapping(value = "/my", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReservationReadDTO> getUsersReservations(@PathVariable("id") long id) throws Exception {
        return readReservationMapper.mapToReservationReadDTOsList(reservationService.getByUser(id));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ReservationReadDTO getById(@PathVariable("id") long id) throws Exception {
        return readReservationMapper.mapToReservationReadDTOsList(Arrays.asList(reservationService.getById(id))).get(0);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean create(@RequestBody ReservationCreateUpdateDTO reservationAddDTO) throws Exception {
        return reservationService.create(createUpdateReservationMapper.mapToReservation(reservationAddDTO));
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") long id) throws Exception {
        return reservationService.delete(id);
    }

    @PutMapping("/update/{id}")
    public boolean update(@PathVariable("id") long id ,@RequestBody ReservationCreateUpdateDTO reservationDetails) throws Exception {
        return reservationService.update(id, createUpdateReservationMapper.mapToReservation(reservationDetails));
    }
}
