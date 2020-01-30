package com.Pulson.RoomReservations.controller;

import com.Pulson.RoomReservations.model.Activity;
import com.Pulson.RoomReservations.model.Room;
import com.Pulson.RoomReservations.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("activities")
public class ActivityController {

    @Autowired
    private ActivityRepository activityRepository;

    @GetMapping("/all")
    public List<Activity> getAll(){
        return activityRepository.findAll();
    }

    @GetMapping("/{id}")
    public Activity getById(@PathVariable("id") long activityId) throws Exception {
        return activityRepository.findById(activityId).orElseThrow(()-> new Exception("Activity "+ activityId +" not found"));
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean create(@RequestBody Activity activity){
        activityRepository.save(activity);
        return true;
    }
}
