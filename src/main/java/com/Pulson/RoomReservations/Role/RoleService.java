package com.Pulson.RoomReservations.Role;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    List<Role> getAll();

    Role findByRoleType(RoleType roleType);
}
