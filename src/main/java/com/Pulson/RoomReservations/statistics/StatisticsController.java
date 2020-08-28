package com.Pulson.RoomReservations.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    public String getHoursByActivityByUser(@PathVariable("id") long userId) throws Exception {
        return statisticsService.getAmountOfHoursSpentOnParticularActivitiesByUser(userId);
    }

    @GetMapping(value = "/timeByMonth/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getHoursByMonthByUser(@PathVariable("id") long userId) throws Exception {
        return statisticsService.getAmountOfHoursSpentInRoomsByMonth(userId);
    }

    @GetMapping(value = "/averageHoursSpentInRoomsPerUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAverageHoursSpentInRoomsPerUser() throws Exception {
        return statisticsService.getAverageHoursSpentInRoomsPerUser();
    }

    @GetMapping(value = "/all/rooms", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HoursPerRoomStatistics> getHoursSpentInRooms() throws Exception {
        return statisticsService.getHoursSpentInRooms();
    }

    @GetMapping(value = "/all/activities", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HoursPerActivityStatistics> getHoursSpentOnActivities() throws Exception {
        return statisticsService.getHoursSpentOnActivities();
    }
}
