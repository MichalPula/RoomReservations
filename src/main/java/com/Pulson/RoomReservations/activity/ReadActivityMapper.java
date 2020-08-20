package com.Pulson.RoomReservations.activity;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReadActivityMapper {

    public List<ActivityCreateReadUpdateDTO> mapToActivityCreateReadUpdateDTOsList(List<Activity> activities) {
        List<ActivityCreateReadUpdateDTO> activityCreateUpdateDTOList = new ArrayList<>();

        activities.forEach(activity -> {
            ActivityCreateReadUpdateDTO activityCreateReadUpdateDTO = new ActivityCreateReadUpdateDTO();
            activityCreateReadUpdateDTO.setId(activity.getId());
            activityCreateReadUpdateDTO.setName(activity.getName());

            List<String> authorities = new ArrayList<>();
            activity.getAuthorities().forEach(role -> {
                switch (role.getAuthority()) {
                    case "ROLE_USER":
                        authorities.add("User");
                        break;
                    case "ROLE_ADMIN":
                        authorities.add("Admin");
                        break;
                }
            });
            activityCreateReadUpdateDTO.setAuthorities(authorities);

            activityCreateReadUpdateDTO.setAvailable(activity.getAvailable());

            activityCreateUpdateDTOList.add(activityCreateReadUpdateDTO);
        });
        return activityCreateUpdateDTOList;
    }
}
