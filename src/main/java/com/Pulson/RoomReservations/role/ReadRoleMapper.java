package com.Pulson.RoomReservations.role;

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
