package com.Pulson.RoomReservations.services;

import com.Pulson.RoomReservations.entities.*;
import com.Pulson.RoomReservations.repositories.*;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class Initializer {
    public Initializer(UserRepository userRepository, RoomRepository roomRepository,
                       ActivityRepository activityRepository, ReservationRepository reservationRepository,
                       UserTypeRepository userTypeRepository){

        List<Role> roles = new ArrayList<>(Arrays.asList(
            new Role("mentor"),
            new Role("student")
        ));
        userTypeRepository.saveAll(roles);

        List<Room> rooms = new ArrayList<>(Arrays.asList(
            new Room("Small wood room 1"),
            new Room("Small wood room 2"),
            new Room("Jerzy's office")
        ));
        roomRepository.saveAll(rooms);

        List<Activity> activities = new ArrayList<>(Arrays.asList(
            new Activity("learning", roles.get(1)),
            new Activity("playing PS4", roles.get(1)),
            new Activity("helping other student", roles.get(1)),
            new Activity("reading a book", roles.get(1)),
            new Activity("playing board games", roles.get(1)),
            new Activity("making my SI assignment", roles.get(1)),
            new Activity("having 1 on 1 conversation", roles.get(0)),
            new Activity("hosting QualityGate", roles.get(0)),
            new Activity("having code review", roles.get(0))
        ));
        activityRepository.saveAll(activities);


        List<User> users = new ArrayList<>(Arrays.asList(
                new User("LeBron", "James", "LB", "lebron@gmail.com", roles.get(1)),
                new User("Kevin", "Durant", "KD", "kd@gmail.com", roles.get(0)),
                new User("Stephen", "Curry", "SC", "steph_curry@gmail.com", roles.get(1)),
                new User("Kyrie", "Irving","KI", "kyrie_irvingh@gmail.com", roles.get(1)),
                new User("Derrick", "Rose","DR", "drose@gmail.com", roles.get(0))
        ));
        userRepository.saveAll(users);


        List<Reservation> reservations = new ArrayList<>(Arrays.asList(
            new Reservation(users.get(0), rooms.get(0), ZonedDateTime.now(), ZonedDateTime.now(),activities.get(8)),
            new Reservation(users.get(1), rooms.get(1), ZonedDateTime.now(), ZonedDateTime.now(),activities.get(4)),
            new Reservation(users.get(2), rooms.get(2), ZonedDateTime.now(), ZonedDateTime.now(),activities.get(3)),
            new Reservation(users.get(3), rooms.get(1), ZonedDateTime.now(), ZonedDateTime.now(),activities.get(6))
        ));
        reservationRepository.saveAll(reservations);
    }
}
