package com.Pulson.RoomReservations.activity;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActivityService {
    List<Activity> getAll();

    Activity getById(long id) throws Exception;

    Boolean create(Activity activity);

    Boolean deactivate(long id);

    Boolean update(long id, Activity activityDetails) throws Exception;
}
