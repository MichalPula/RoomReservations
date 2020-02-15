package com.Pulson.RoomReservations.services;

import com.Pulson.RoomReservations.entities.*;
import com.Pulson.RoomReservations.repositories.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class Initializer {
    public Initializer(UserRepository userRepository, RoomRepository roomRepository,
                       ActivityRepository activityRepository, ReservationRepository reservationRepository,
                       RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder){



        for(RoleType roleType : RoleType.values()){
            Role role = new Role();
            role.setRole(roleType.name());
            roleRepository.save(role);
        }

        final String adminPassword = bCryptPasswordEncoder.encode("admin");
        final String userPassword = bCryptPasswordEncoder.encode("user");
        List<User> users = new ArrayList<>();
        User admin = new User("LeBron", "James", "pulson@wp.pl", adminPassword,
                true, List.of(roleRepository.findRoleByRole(RoleType.ROLE_USER.name()), roleRepository.findRoleByRole(RoleType.ROLE_ADMIN.name())));
        User user = new User("Derrick", "Rose", "user", userPassword,
                true, List.of(roleRepository.findRoleByRole(RoleType.ROLE_USER.name())));

        users.add(admin);
        users.add(user);
        userRepository.saveAll(users);
    }

//        List<Room> rooms = new ArrayList<>(Arrays.asList(
//            new Room("Small wood room 1"),
//            new Room("Small wood room 2"),
//            new Room("Jerzy's office")
//        ));
//        roomRepository.saveAll(rooms);

//        List<Activity> activities = new ArrayList<>(Arrays.asList(
//            new Activity("learning", roles.get(1)),
//            new Activity("playing PS4", roles.get(1)),
//            new Activity("helping other student", roles.get(1)),
//            new Activity("reading a book", roles.get(1)),
//            new Activity("playing board games", roles.get(1)),
//            new Activity("making my SI assignment", roles.get(1)),
//            new Activity("having 1 on 1 conversation", roles.get(0)),
//            new Activity("hosting QualityGate", roles.get(0)),
//            new Activity("having code review", roles.get(0))
//        ));
//        activityRepository.saveAll(activities);
//
//
//        List<Reservation> reservations = new ArrayList<>(Arrays.asList(
//            new Reservation(users.get(0), rooms.get(0), ZonedDateTime.now(), ZonedDateTime.now(),activities.get(8)),
//            new Reservation(users.get(1), rooms.get(1), ZonedDateTime.now(), ZonedDateTime.now(),activities.get(4)),
//            new Reservation(users.get(2), rooms.get(2), ZonedDateTime.now(), ZonedDateTime.now(),activities.get(3)),
//            new Reservation(users.get(3), rooms.get(1), ZonedDateTime.now(), ZonedDateTime.now(),activities.get(6))
//        ));
//        reservationRepository.saveAll(reservations);
}
