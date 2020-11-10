package com.Pulson.RoomReservations.activity;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActivityService {
    List<Activity> getAll();

    String create(Activity activity);

    String deactivate(long id);

    String update(long id, Activity activityDetails);
}
