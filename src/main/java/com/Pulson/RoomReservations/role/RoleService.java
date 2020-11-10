package com.Pulson.RoomReservations.role;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    List<Role> getAll();

    Role findByRoleType(RoleType roleType);
}
