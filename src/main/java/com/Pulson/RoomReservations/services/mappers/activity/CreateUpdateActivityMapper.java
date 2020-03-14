package com.Pulson.RoomReservations.services.mappers.activity;

import com.Pulson.RoomReservations.entities.Activity;
import com.Pulson.RoomReservations.entities.Role;
import com.Pulson.RoomReservations.entities.dtos.activity.ActivityCreateReadUpdateDTO;
import com.Pulson.RoomReservations.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateUpdateActivityMapper {

    @Autowired
    private RoleService roleService;

    public Activity mapToActivity(ActivityCreateReadUpdateDTO activityCreateReadUpdateDTO) throws Exception {
        Activity activity = new Activity();
        activity.setName(activityCreateReadUpdateDTO.getName());
        activity.setAvailable(activityCreateReadUpdateDTO.getAvailable());

        List<Role> authorities = new ArrayList<>();
        activity.getAuthorities().forEach(role -> {
            authorities.add(roleService.findByRoleType(role.getRoleType()));
        });
        activity.setAuthorities(authorities);

        return activity;
    }
}
