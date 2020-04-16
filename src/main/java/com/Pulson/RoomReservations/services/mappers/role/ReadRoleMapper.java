package com.Pulson.RoomReservations.services.mappers.role;

import com.Pulson.RoomReservations.entities.Role;
import com.Pulson.RoomReservations.entities.dtos.role.RoleReadDTO;
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
