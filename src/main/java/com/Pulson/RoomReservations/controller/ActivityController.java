package com.Pulson.RoomReservations.controller;

import com.Pulson.RoomReservations.model.Activity;
import com.Pulson.RoomReservations.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping("/all")
    public List<Activity> getAll(){
        return activityService.getAll();
    }

    @GetMapping("/{id}")
    public Activity getById(@PathVariable("id") long id) throws Exception {
        return activityService.getById(id);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Boolean create(@RequestBody Activity activity){
        return activityService.create(activity);
    }

    @DeleteMapping("/deactivate/{id}")
    public boolean deactivate(@PathVariable("id") long id) throws Exception {
        return activityService.deactivate(id);
    }

    @PutMapping("/update/{id}")
    public boolean update(@PathVariable("id") long id, @RequestBody Activity activityDetails) throws Exception {
        return activityService.update(id, activityDetails);
    }
}
