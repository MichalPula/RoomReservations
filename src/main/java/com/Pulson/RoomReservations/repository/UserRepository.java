package com.Pulson.RoomReservations.repository;

import com.Pulson.RoomReservations.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
