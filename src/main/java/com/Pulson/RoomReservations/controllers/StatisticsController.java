package com.Pulson.RoomReservations.controllers;

import com.Pulson.RoomReservations.entities.dtos.reservation.ReservationReadDTO;
import com.Pulson.RoomReservations.services.StatisticsService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("statistics")
public class StatisticsController {

    private StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService){
        this.statisticsService = statisticsService;
    }

    @GetMapping(value = "/timeByActivity/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAll(@PathVariable("id") long userId) throws Exception { ;
        return statisticsService.getAmountOfHoursSpentOnParticularActivitiesByUser(userId);
    }
}


