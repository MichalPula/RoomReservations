package com.Pulson.RoomReservations.repositories;

import com.Pulson.RoomReservations.entities.Role;
import com.Pulson.RoomReservations.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByUsername(String username);

    Boolean existsByUsername(String username);

    List<User> findAllByRolesNotContaining(Role notContaining);

    List<User> findAllByRolesNotContainingAndFirstNameIgnoreCaseAndLastNameIgnoreCase(Role notContaining, String firstName, String lastName);
}
