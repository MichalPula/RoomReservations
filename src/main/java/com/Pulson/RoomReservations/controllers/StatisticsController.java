package com.Pulson.RoomReservations.controllers;

import com.Pulson.RoomReservations.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    public String getHoursByActivityByUser(@PathVariable("id") long userId) throws Exception { ;
        return statisticsService.getAmountOfHoursSpentOnParticularActivitiesByUser(userId);
    }

    @GetMapping(value = "/timeByMonth/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getHoursByMonthByUser(@PathVariable("id") long userId) throws Exception { ;
        return statisticsService.getAmountOfHoursSpentInRoomsByMonth(userId);
    }

    @GetMapping(value = "/averageTimeByMonthOfAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAverageHoursByMonthOfAllUsers() throws Exception { ;
        return statisticsService.getAverageHoursPerMonthOfAllUsers();
    }
}


