package com.Pulson.RoomReservations.services;

import com.Pulson.RoomReservations.entities.*;
import com.Pulson.RoomReservations.repositories.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class Initializer {
    public Initializer(UserRepository userRepository, RoomRepository roomRepository,
                       ActivityRepository activityRepository, ReservationRepository reservationRepository,
                       RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder, ReservationService reservationService) throws Exception {


        for (RoleType roleType : RoleType.values()) {
            Role role = new Role();
            role.setRoleType(roleType);
            roleRepository.save(role);
        }

        final String adminPassword = bCryptPasswordEncoder.encode("admin123");
        final String userPassword = bCryptPasswordEncoder.encode("user123");
        List<User> users = new ArrayList<>();
        User admin = new User("LeBron", "James", "pulson@wp.pl", adminPassword,
                Set.of(roleRepository.findByRoleType(RoleType.ROLE_USER), roleRepository.findByRoleType(RoleType.ROLE_ADMIN)));
        User user = new User("Derrick", "Rose", "pleb@wp.pl", userPassword,
                 Set.of(roleRepository.findByRoleType(RoleType.ROLE_USER)));

        users.add(admin);
        users.add(user);
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
                new Reservation(users.get(1), rooms.get(2), LocalDateTime.of(2019, 5, 10, 15, 0), LocalDateTime.of(2019, 5, 10, 17, 0), activities.get(1)),
                new Reservation(users.get(1), rooms.get(3), LocalDateTime.of(2019, 8, 22, 10, 30), LocalDateTime.of(2019, 8, 22, 11, 30), activities.get(0)),
                new Reservation(users.get(1), rooms.get(3), LocalDateTime.of(2019, 8, 22, 10, 30), LocalDateTime.of(2019, 8, 22, 11, 30), activities.get(0)),
                new Reservation(users.get(1), rooms.get(3), LocalDateTime.of(2019, 8, 22, 10, 30), LocalDateTime.of(2019, 8, 22, 11, 30), activities.get(0)),
                new Reservation(users.get(1), rooms.get(3), LocalDateTime.of(2019, 8, 16, 10, 30), LocalDateTime.of(2019, 8, 16, 11, 30), activities.get(0)),
                new Reservation(users.get(1), rooms.get(3), LocalDateTime.of(2021, 8, 22, 10, 30), LocalDateTime.of(2021, 8, 22, 11, 30), activities.get(0)),
                new Reservation(users.get(1), rooms.get(3), LocalDateTime.of(2021, 8, 22, 10, 30), LocalDateTime.of(2021, 8, 22, 11, 30), activities.get(0)),
                new Reservation(users.get(1), rooms.get(3), LocalDateTime.of(2020, 3, 11, 14, 0), LocalDateTime.of(2020, 3, 11, 15, 0), activities.get(0)),
                new Reservation(users.get(1), rooms.get(3), LocalDateTime.of(2020, 3, 11, 13, 0), LocalDateTime.of(2020, 3, 11, 14, 0), activities.get(0)),
                new Reservation(users.get(1), rooms.get(3), LocalDateTime.of(2020, 3, 16, 9, 0), LocalDateTime.of(2020, 3, 16, 10, 0), activities.get(0)),
                new Reservation(users.get(0), rooms.get(1), LocalDateTime.of(2020, 3, 16, 13, 0), LocalDateTime.of(2020, 3, 16, 14, 0), activities.get(0)),
                new Reservation(users.get(0), rooms.get(1), LocalDateTime.of(2020, 3, 16, 14, 0), LocalDateTime.of(2020, 3, 16, 15, 0), activities.get(0))
        ));
        reservationRepository.saveAll(reservations);


//        List<Integer> xd = reservationService.getTodayReservationsStartingHours(2020, 3, 10);
//        xd.forEach(integer -> {
//            System.out.println(xd);
//        });
    }
}

