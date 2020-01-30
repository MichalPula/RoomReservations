package com.Pulson.RoomReservations.controller;

import com.Pulson.RoomReservations.model.Activity;
import com.Pulson.RoomReservations.model.User;
import com.Pulson.RoomReservations.model.UserType;
import com.Pulson.RoomReservations.repository.ActivityRepository;
import com.Pulson.RoomReservations.repository.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("activities")
public class ActivityController {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private UserTypeRepository userTypeRepository;

    @GetMapping("/all")
    public List<Activity> getAll(){
        return activityRepository.findAll();
    }

    @GetMapping("/{id}")
    public Activity getById(@PathVariable("id") long activityId) throws Exception {
        return activityRepository.findById(activityId).orElseThrow(()-> new Exception("Activity "+ activityId +" not found"));
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Activity create(@RequestBody Activity activity){
        UserType ut = userTypeRepository.findByName(activity.getUserType().getName());
        activity.setUserType(ut);
        return activityRepository.save(activity);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") long activityId) throws Exception {
        activityRepository.delete(activityRepository.findById(activityId).orElseThrow(() -> new Exception("Activity has NOT been removed")));
        return true;
    }

    @PutMapping("/update/{id}")
    public boolean update(@PathVariable("id") long activityId, @RequestBody Activity activityDetails) throws Exception {
        Activity activity = activityRepository.findById(activityId).orElseThrow(() -> new Exception("Activity NOT found"));
        activity.setName(activityDetails.getName());
        activity.setUserType(activityDetails.getUserType());
        activityRepository.save(activity);
        return true;
    }
}
