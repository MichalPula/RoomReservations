package com.Pulson.RoomReservations.services;

import com.Pulson.RoomReservations.entities.Role;
import com.Pulson.RoomReservations.entities.RoleType;

public interface RoleService {
    Role findByRoleType(RoleType roleType);
}
