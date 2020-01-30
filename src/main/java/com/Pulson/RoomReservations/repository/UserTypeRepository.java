package com.Pulson.RoomReservations.repository;

import com.Pulson.RoomReservations.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Long> {

    UserType findByName(String name);
}
