package com.Pulson.RoomReservations.services;

import com.Pulson.RoomReservations.entities.Role;
import com.Pulson.RoomReservations.entities.RoleType;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    Role findByRoleType(RoleType roleType);
}
