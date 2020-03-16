package com.Pulson.RoomReservations.controllers;

import com.Pulson.RoomReservations.entities.dtos.reservation.ReservationCreateUpdateDTO;
import com.Pulson.RoomReservations.entities.dtos.reservation.ReservationReadDTO;
import com.Pulson.RoomReservations.services.ReservationService;
import com.Pulson.RoomReservations.services.mappers.reservation.CreateUpdateReservationMapper;
import com.Pulson.RoomReservations.services.mappers.reservation.ReadReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping(value = "/active/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReservationReadDTO> getUsersActiveReservations(@PathVariable("id") long id) throws Exception {
        return readReservationMapper.mapToReservationReadDTOsList(reservationService.getActiveByUser(id));
    }

    @GetMapping(value = "/history/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReservationReadDTO> getUsersReservationsHistory(@PathVariable("id") long id) throws Exception {
        return readReservationMapper.mapToReservationReadDTOsList(reservationService.getHistoryByUser(id));
    }

    @GetMapping(value = "/date/{year}/{month}/{day}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Integer> getTodayReservationsStartingHours(@PathVariable("year") int year, @PathVariable("month") int month,
                                                           @PathVariable("day") int day) throws Exception {
        return reservationService.getStartingHoursListByDate(year, month, day);
    }

    @GetMapping(value = "/date/{year}/{month}/{day}/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer getNumberOfReservationsPerDayPerUser(@PathVariable("year") int year, @PathVariable("month") int month,
                                                        @PathVariable("day") int day, @PathVariable("userId") long userId) throws Exception {
        return reservationService.getAmountByDateByUser(year, month, day, userId);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ReservationReadDTO getById(@PathVariable("id") long id) throws Exception {
        return readReservationMapper.mapToReservationReadDTOsList(Arrays.asList(reservationService.getById(id))).get(0);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean create(@RequestBody ReservationCreateUpdateDTO reservationCreateUpdateDTO) throws Exception {
        return reservationService.create(createUpdateReservationMapper.mapToReservation(reservationCreateUpdateDTO));
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") long id) throws Exception {
        return reservationService.delete(id);
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean update(@PathVariable("id") long id ,@RequestBody ReservationCreateUpdateDTO reservationDetails) throws Exception {
        return reservationService.update(id, createUpdateReservationMapper.mapToReservation(reservationDetails));
    }

}
