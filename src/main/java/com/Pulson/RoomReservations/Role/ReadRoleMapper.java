package com.Pulson.RoomReservations.Role;

import com.Pulson.RoomReservations.Role.Role;
import com.Pulson.RoomReservations.Role.RoleReadDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReadRoleMapper {

    public List<RoleReadDTO> mapToRoleReadDTOsList(List<Role> roles) {
        List<RoleReadDTO> roleReadDTOList = new ArrayList<>();

        roles.forEach(role -> {
            RoleReadDTO roleReadDTO = new RoleReadDTO();
            switch (role.getAuthority()) {
                case "ROLE_USER":
                    roleReadDTO.setFormattedRoleType("User");
                    break;
                case "ROLE_ADMIN":
                    roleReadDTO.setFormattedRoleType("Admin");
                    break;
            }
            roleReadDTOList.add(roleReadDTO);
        });
        return roleReadDTOList;
    }
}
