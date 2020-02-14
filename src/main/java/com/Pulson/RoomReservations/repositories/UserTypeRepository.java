package com.Pulson.RoomReservations.repositories;

import com.Pulson.RoomReservations.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
