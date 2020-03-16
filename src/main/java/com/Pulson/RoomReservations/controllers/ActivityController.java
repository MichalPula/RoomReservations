package com.Pulson.RoomReservations.controllers;

import com.Pulson.RoomReservations.entities.Activity;
import com.Pulson.RoomReservations.entities.dtos.activity.ActivityCreateReadUpdateDTO;
import com.Pulson.RoomReservations.services.ActivityService;
import com.Pulson.RoomReservations.services.mappers.activity.CreateUpdateActivityMapper;
import com.Pulson.RoomReservations.services.mappers.activity.ReadActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private CreateUpdateActivityMapper createUpdateActivityMapper;

    @Autowired
    private ReadActivityMapper readActivityMapper;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ActivityCreateReadUpdateDTO> getAll(){
        return readActivityMapper.mapToActivityCreateUpdateDTOsList(activityService.getAll());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ActivityCreateReadUpdateDTO getById(@PathVariable("id") long id) throws Exception {
        return readActivityMapper.mapToActivityCreateUpdateDTOsList(Arrays.asList(activityService.getById(id))).get(0);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean create(@RequestBody ActivityCreateReadUpdateDTO activityCreateReadUpdateDTO) throws Exception {
        return activityService.create(createUpdateActivityMapper.mapToActivity(activityCreateReadUpdateDTO));
    }

    @DeleteMapping("/deactivate/{id}")
    public boolean deactivate(@PathVariable("id") long id) throws Exception {
        return activityService.deactivate(id);
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean update(@PathVariable("id") long id, @RequestBody Activity activityDetails) throws Exception {
        return activityService.update(id, activityDetails);
    }
}
