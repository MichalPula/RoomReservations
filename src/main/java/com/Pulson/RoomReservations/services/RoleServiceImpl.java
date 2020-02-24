package com.Pulson.RoomReservations.services;

import com.Pulson.RoomReservations.entities.Role;
import com.Pulson.RoomReservations.entities.RoleType;
import com.Pulson.RoomReservations.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByRoleType(RoleType roleType) {
        return roleRepository.findByRoleType(roleType);
    }
}
