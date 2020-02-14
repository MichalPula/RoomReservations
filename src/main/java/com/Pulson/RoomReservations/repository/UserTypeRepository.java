package com.Pulson.RoomReservations.repository;

import com.Pulson.RoomReservations.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
