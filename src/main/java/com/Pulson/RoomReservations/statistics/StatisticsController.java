package com.Pulson.RoomReservations.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping(value = "/timeByActivity/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<HoursPerActivityPerUser>> getHoursByActivityByUser(@PathVariable("id") long userId) {
        return ResponseEntity.ok().body(statisticsService.getAmountOfHoursSpentOnParticularActivitiesByUser(userId));
    }

    @GetMapping(value = "/timeByMonth/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<HoursPerMonthPerUser>> getHoursByMonthByUser(@PathVariable("id") long userId) throws Exception {
        return ResponseEntity.ok().body(statisticsService.getAmountOfHoursSpentInRoomsByMonth(userId));
    }

    @GetMapping(value = "/averageHoursSpentInRoomsPerUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BigDecimal> getAverageHoursSpentInRoomsPerUser() {
        return ResponseEntity.ok().body(statisticsService.getAverageHoursSpentInRoomsPerUser());
    }

    @GetMapping(value = "/all/rooms", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<HoursPerRoomStatistics>> getHoursSpentInRooms() {
        return ResponseEntity.ok().body(statisticsService.getHoursSpentInRooms());
    }

    @GetMapping(value = "/all/activities", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<HoursPerActivityStatistics>> getHoursSpentOnActivities() {
        return ResponseEntity.ok().body(statisticsService.getHoursSpentOnActivities());
    }
}
