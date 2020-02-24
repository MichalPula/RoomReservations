package com.Pulson.RoomReservations.services;

import com.Pulson.RoomReservations.entities.*;
import com.Pulson.RoomReservations.repositories.*;
import org.springframework.security.core.GrantedAuthority;
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
                       RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {


        for (RoleType roleType : RoleType.values()) {
            Role role = new Role();
            role.setRoleType(roleType);
            roleRepository.save(role);
        }

        final String adminPassword = bCryptPasswordEncoder.encode("admin");
        final String userPassword = bCryptPasswordEncoder.encode("user");
        List<User> users = new ArrayList<>();
//        User admin = new User("LeBron", "James", "pulson@wp.pl", adminPassword,
//                true, List.of(roleRepository.findByRoleType(RoleType.ROLE_USER), roleRepository.findByRoleType(RoleType.ROLE_ADMIN)));
//        User user = new User("Derrick", "Rose", "user", userPassword,
//                true, List.of(roleRepository.findByRoleType(RoleType.ROLE_USER)));
//
//        users.add(admin);
//        users.add(user);
        userRepository.saveAll(users);


        List<Room> rooms = new ArrayList<>(Arrays.asList(
                new Room("Small wood room 1"),
                new Room("Small wood room 2"),
                new Room("Jerzy's office"),
                new Room("Room")
        ));
        roomRepository.saveAll(rooms);


        List<Role> adminOnly = new ArrayList<>(Arrays.asList(roleRepository.findByRoleType(RoleType.ROLE_ADMIN)));
        List<Role> userOnly = new ArrayList<>(Arrays.asList(roleRepository.findByRoleType(RoleType.ROLE_USER)));
        List<Role> adminAndUser = new ArrayList<>(Arrays.asList(roleRepository.findByRoleType(RoleType.ROLE_ADMIN),
                roleRepository.findByRoleType(RoleType.ROLE_USER)));
        List<Activity> activities = new ArrayList<>(Arrays.asList(
                new Activity("learning", userOnly),
                new Activity("playing PS4", userOnly),
                new Activity("helping other student", userOnly),
                new Activity("reading a book", userOnly),
                new Activity("playing board games", adminAndUser),
                new Activity("making my SI assignment", userOnly),
                new Activity("having 1 on 1 conversation", adminOnly),
                new Activity("hosting QualityGate", adminOnly),
                new Activity("having code review", adminOnly)
        ));
        activityRepository.saveAll(activities);


        List<Reservation> reservations = new ArrayList<>(Arrays.asList(
                new Reservation(users.get(0), rooms.get(0), ZonedDateTime.now(), ZonedDateTime.now(), activities.get(8)),
                new Reservation(users.get(1), rooms.get(1), ZonedDateTime.now(), ZonedDateTime.now(), activities.get(4)),
                new Reservation(users.get(1), rooms.get(2), ZonedDateTime.now(), ZonedDateTime.now(), activities.get(3)),
                new Reservation(users.get(1), rooms.get(1), ZonedDateTime.now(), ZonedDateTime.now(), activities.get(6))
        ));
        reservationRepository.saveAll(reservations);
    }
}

