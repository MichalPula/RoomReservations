package com.Pulson.RoomReservations.repositories;

import com.Pulson.RoomReservations.entities.Role;
import com.Pulson.RoomReservations.entities.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleType(RoleType roleType);
}
