package com.Pulson.RoomReservations.activity;

import com.Pulson.RoomReservations.role.Role;
import com.Pulson.RoomReservations.role.RoleService;
import com.Pulson.RoomReservations.role.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateUpdateActivityMapper {

    private RoleService roleService;

    @Autowired
    private CreateUpdateActivityMapper(RoleService roleService) {
        this.roleService = roleService;
    }

    public Activity mapToActivity(ActivityCreateReadUpdateDTO activityCreateReadUpdateDTO) throws Exception {
        Activity activity = new Activity();
        activity.setName(activityCreateReadUpdateDTO.getName());
        activity.setAvailable(activityCreateReadUpdateDTO.getAvailable());

        List<Role> authorities = new ArrayList<>();
        activityCreateReadUpdateDTO.getAuthorities().forEach(role -> {
            switch (role) {
                case "User":
                    authorities.add(roleService.findByRoleType(RoleType.ROLE_USER));
                    break;
                case "Admin":
                    authorities.add(roleService.findByRoleType(RoleType.ROLE_ADMIN));
                    break;
            }
        });
        activity.setAuthorities(authorities);
        return activity;
    }
}
