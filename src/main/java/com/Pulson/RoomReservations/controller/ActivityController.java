package com.Pulson.RoomReservations.controller;

import com.Pulson.RoomReservations.model.Activity;
import com.Pulson.RoomReservations.model.Room;
import com.Pulson.RoomReservations.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
