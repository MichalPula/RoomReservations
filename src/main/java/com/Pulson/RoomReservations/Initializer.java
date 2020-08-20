package com.Pulson.RoomReservations;

import com.Pulson.RoomReservations.activity.Activity;
import com.Pulson.RoomReservations.activity.ActivityRepository;
import com.Pulson.RoomReservations.reservation.Reservation;
import com.Pulson.RoomReservations.reservation.ReservationRepository;
import com.Pulson.RoomReservations.reservation.ReservationService;
import com.Pulson.RoomReservations.role.Role;
import com.Pulson.RoomReservations.role.RoleRepository;
import com.Pulson.RoomReservations.role.RoleType;
import com.Pulson.RoomReservations.room.Room;
import com.Pulson.RoomReservations.room.RoomRepository;
import com.Pulson.RoomReservations.statistics.StatisticsService;
import com.Pulson.RoomReservations.user.User;
import com.Pulson.RoomReservations.user.UserRepository;
import com.Pulson.RoomReservations.user.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class Initializer {
    public Initializer(UserRepository userRepository, RoomRepository roomRepository,
                       ActivityRepository activityRepository, ReservationRepository reservationRepository,
                       RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder, ReservationService reservationService,
                       StatisticsService statisticsService, UserService userService) throws Exception {

        for (RoleType roleType : RoleType.values()) {
            Role role = new Role();
            role.setRoleType(roleType);
            roleRepository.save(role);
        }

        final String adminPassword = bCryptPasswordEncoder.encode("admin123");
        final String userPassword = bCryptPasswordEncoder.encode("user123");
        List<User> users = new ArrayList<>();
        User admin = new User("LeBron", "James", 123456789, "pulson@wp.pl", adminPassword,
                Set.of(roleRepository.findByRoleType(RoleType.ROLE_USER), roleRepository.findByRoleType(RoleType.ROLE_ADMIN)));
        User user1 = new User("Derrick", "Rose", 987654321, "pleb@wp.pl", userPassword,
                Set.of(roleRepository.findByRoleType(RoleType.ROLE_USER)));
        User user2 = new User("Derrick", "rose", 653276178, "kawhi", userPassword,
                Set.of(roleRepository.findByRoleType(RoleType.ROLE_USER)));
        User user3 = new User("Stephen", "Curry", 924167356, "steph", userPassword,
                Set.of(roleRepository.findByRoleType(RoleType.ROLE_USER)));

        users.add(admin);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        userRepository.saveAll(users);


        List<Room> rooms = new ArrayList<>(Arrays.asList(
                new Room("Small wood room 1", true),
                new Room("Small wood room 2", true),
                new Room("Jerzy's office", true),
                new Room("Room", true)
        ));
        roomRepository.saveAll(rooms);


        List<Role> adminOnly = new ArrayList<>(Arrays.asList(roleRepository.findByRoleType(RoleType.ROLE_ADMIN)));
        List<Role> userOnly = new ArrayList<>(Arrays.asList(roleRepository.findByRoleType(RoleType.ROLE_USER)));
        List<Role> adminAndUser = new ArrayList<>(Arrays.asList(roleRepository.findByRoleType(RoleType.ROLE_ADMIN),
                roleRepository.findByRoleType(RoleType.ROLE_USER)));
        List<Activity> activities = new ArrayList<>(Arrays.asList(
                new Activity("Learning", userOnly),
                new Activity("Playing PS4", userOnly),
                new Activity("Helping other student", userOnly),
                new Activity("Reading a book", userOnly),
                new Activity("Making my SI assignment", userOnly),
                new Activity("Playing board games", userOnly),
                new Activity("Having 1 on 1 conversation", adminOnly),
                new Activity("Hosting QualityGate", adminOnly),
                new Activity("Having code review", adminOnly)
        ));
        activityRepository.saveAll(activities);


        List<Reservation> reservations = new ArrayList<>(Arrays.asList(
                new Reservation(users.get(2), rooms.get(3), LocalDateTime.of(2020, 4, 22, 21, 30), LocalDateTime.of(2020, 4, 9, 22, 30), activities.get(0)),
                new Reservation(users.get(3), rooms.get(3), LocalDateTime.of(2020, 4, 10, 10, 30), LocalDateTime.of(2020, 4, 10, 11, 30), activities.get(1)),
                new Reservation(users.get(1), rooms.get(3), LocalDateTime.of(2020, 1, 22, 10, 30), LocalDateTime.of(2020, 1, 22, 11, 30), activities.get(2)),
                new Reservation(users.get(1), rooms.get(3), LocalDateTime.of(2020, 3, 22, 10, 30), LocalDateTime.of(2020, 3, 22, 11, 30), activities.get(3)),
                new Reservation(users.get(1), rooms.get(3), LocalDateTime.of(2020, 1, 22, 10, 30), LocalDateTime.of(2020, 1, 22, 11, 30), activities.get(4)),
                new Reservation(users.get(1), rooms.get(3), LocalDateTime.of(2020, 1, 22, 10, 30), LocalDateTime.of(2020, 1, 22, 11, 30), activities.get(2)),
                new Reservation(users.get(1), rooms.get(3), LocalDateTime.of(2020, 2, 22, 10, 30), LocalDateTime.of(2020, 2, 22, 11, 30), activities.get(1)),
                new Reservation(users.get(1), rooms.get(3), LocalDateTime.of(2020, 3, 22, 10, 30), LocalDateTime.of(2020, 3, 22, 11, 30), activities.get(2)),
                new Reservation(users.get(1), rooms.get(3), LocalDateTime.of(2020, 1, 22, 10, 30), LocalDateTime.of(2020, 1, 22, 11, 30), activities.get(2)),
                new Reservation(users.get(1), rooms.get(3), LocalDateTime.of(2020, 1, 22, 10, 30), LocalDateTime.of(2020, 1, 22, 11, 30), activities.get(4)),
                new Reservation(users.get(1), rooms.get(3), LocalDateTime.of(2020, 1, 22, 10, 30), LocalDateTime.of(2020, 1, 22, 11, 30), activities.get(4)),
                new Reservation(users.get(1), rooms.get(3), LocalDateTime.of(2020, 2, 22, 10, 30), LocalDateTime.of(2020, 2, 22, 11, 30), activities.get(1)),
                new Reservation(users.get(1), rooms.get(3), LocalDateTime.of(2020, 2, 22, 10, 30), LocalDateTime.of(2020, 2, 22, 11, 30), activities.get(1)),
                new Reservation(users.get(1), rooms.get(3), LocalDateTime.of(2020, 2, 22, 10, 30), LocalDateTime.of(2020, 2, 22, 11, 30), activities.get(4)),
                new Reservation(users.get(1), rooms.get(3), LocalDateTime.of(2020, 2, 22, 10, 30), LocalDateTime.of(2020, 2, 22, 11, 30), activities.get(4)),
                new Reservation(users.get(1), rooms.get(3), LocalDateTime.of(2020, 3, 22, 10, 30), LocalDateTime.of(2020, 3, 22, 11, 30), activities.get(4)),
                new Reservation(users.get(1), rooms.get(3), LocalDateTime.of(2020, 1, 22, 10, 30), LocalDateTime.of(2020, 1, 22, 11, 30), activities.get(4)),
                new Reservation(users.get(1), rooms.get(3), LocalDateTime.of(2020, 1, 22, 10, 30), LocalDateTime.of(2020, 1, 22, 11, 30), activities.get(2)),
                new Reservation(users.get(1), rooms.get(3), LocalDateTime.of(2020, 1, 22, 10, 30), LocalDateTime.of(2020, 1, 22, 11, 30), activities.get(3)),
                new Reservation(users.get(1), rooms.get(3), LocalDateTime.of(2020, 1, 22, 10, 30), LocalDateTime.of(2020, 1, 22, 11, 30), activities.get(3)),
                new Reservation(users.get(1), rooms.get(3), LocalDateTime.of(2020, 1, 22, 10, 30), LocalDateTime.of(2020, 1, 22, 11, 30), activities.get(1)),
                new Reservation(users.get(1), rooms.get(3), LocalDateTime.of(2020, 2, 22, 10, 30), LocalDateTime.of(2020, 2, 22, 11, 30), activities.get(4)),
                new Reservation(users.get(1), rooms.get(3), LocalDateTime.of(2020, 1, 22, 10, 30), LocalDateTime.of(2020, 1, 22, 11, 30), activities.get(1)),
                new Reservation(users.get(1), rooms.get(3), LocalDateTime.of(2020, 2, 22, 10, 30), LocalDateTime.of(2020, 2, 22, 11, 30), activities.get(4)),
                new Reservation(users.get(1), rooms.get(3), LocalDateTime.of(2020, 1, 22, 10, 30), LocalDateTime.of(2020, 1, 22, 11, 30), activities.get(2)),
                new Reservation(users.get(1), rooms.get(3), LocalDateTime.of(2020, 1, 22, 10, 30), LocalDateTime.of(2020, 1, 22, 11, 30), activities.get(2)),
                new Reservation(users.get(1), rooms.get(0), LocalDateTime.of(2020, 1, 22, 10, 30), LocalDateTime.of(2020, 1, 22, 11, 30), activities.get(2)),
                new Reservation(users.get(1), rooms.get(0), LocalDateTime.of(2020, 1, 22, 10, 30), LocalDateTime.of(2020, 1, 22, 11, 30), activities.get(0)),
                new Reservation(users.get(1), rooms.get(0), LocalDateTime.of(2020, 2, 16, 10, 30), LocalDateTime.of(2020, 2, 16, 11, 30), activities.get(1)),
                new Reservation(users.get(1), rooms.get(0), LocalDateTime.of(2020, 3, 11, 14, 0), LocalDateTime.of(2020, 3, 11, 15, 0), activities.get(0)),
                new Reservation(users.get(1), rooms.get(2), LocalDateTime.of(2020, 4, 6, 13, 0), LocalDateTime.of(2020, 4, 6, 14, 0), activities.get(0)),
                new Reservation(users.get(1), rooms.get(1), LocalDateTime.of(2020, 4, 6, 9, 0), LocalDateTime.of(2020, 4, 6, 10, 0), activities.get(0)),
                new Reservation(users.get(0), rooms.get(1), LocalDateTime.of(2020, 4, 3, 13, 0), LocalDateTime.of(2020, 4, 3, 14, 0), activities.get(6)),
                new Reservation(users.get(0), rooms.get(1), LocalDateTime.of(2020, 4, 3, 9, 0), LocalDateTime.of(2020, 4, 3, 10, 0), activities.get(7)),
                new Reservation(users.get(0), rooms.get(1), LocalDateTime.of(2020, 4, 3, 22, 0), LocalDateTime.of(2020, 4, 3, 23, 0), activities.get(8))
        ));
        reservationRepository.saveAll(reservations);
    }
}
