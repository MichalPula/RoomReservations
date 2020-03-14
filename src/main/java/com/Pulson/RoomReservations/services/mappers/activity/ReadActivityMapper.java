package com.Pulson.RoomReservations.services.mappers.activity;

import com.Pulson.RoomReservations.entities.Activity;
import com.Pulson.RoomReservations.entities.dtos.activity.ActivityCreateReadUpdateDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReadActivityMapper {

    public List<ActivityCreateReadUpdateDTO> mapToActivityCreateUpdateDTOsList(List<Activity> activities){
        List<ActivityCreateReadUpdateDTO> activityCreateUpdateDTOList = new ArrayList<>();

        activities.forEach(activity -> {
            ActivityCreateReadUpdateDTO activityCreateReadUpdateDTO = new ActivityCreateReadUpdateDTO();
            activityCreateReadUpdateDTO.setName(activity.getName());

            List<String> authorities = new ArrayList<>();
            activity.getAuthorities().forEach(role -> authorities.add(role.getAuthority()));
            activityCreateReadUpdateDTO.setAuthorities(authorities);

            activityCreateReadUpdateDTO.setAvailable(activity.getAvailable());

            activityCreateUpdateDTOList.add(activityCreateReadUpdateDTO);
        });
        return activityCreateUpdateDTOList;
    }
}
