package com.Pulson.RoomReservations.service;

import com.Pulson.RoomReservations.model.Activity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActivityService {
    List<Activity> getAll();
    Activity getById(long id) throws Exception;
    Boolean create(Activity activity);
    Boolean deactivate(long id) throws Exception;
    Boolean update(long id, Activity activityDetails) throws Exception;
}
