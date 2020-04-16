package com.Pulson.RoomReservations.services;

import com.Pulson.RoomReservations.entities.Role;
import com.Pulson.RoomReservations.entities.RoleType;
import com.Pulson.RoomReservations.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findByRoleType(RoleType roleType) {
        return roleRepository.findByRoleType(roleType);
    }
}
