package com.Pulson.RoomReservations.service;

import com.Pulson.RoomReservations.model.*;
import com.Pulson.RoomReservations.repository.*;
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

        List<UserType> userTypes = new ArrayList<>(Arrays.asList(
            new UserType("mentor"),
            new UserType("student")
        ));
        userTypeRepository.saveAll(userTypes);

        List<Room> rooms = new ArrayList<>(Arrays.asList(
            new Room("Small wood room 1"),
            new Room("Small wood room 2"),
            new Room("Jerzy's office")
        ));
        roomRepository.saveAll(rooms);

        List<Activity> activities = new ArrayList<>(Arrays.asList(
            new Activity("learning", userTypes.get(2)),
            new Activity("playing PS4",userTypes.get(2)),
            new Activity("helping other student",userTypes.get(2)),
            new Activity("reading a book",userTypes.get(2)),
            new Activity("playing board games",userTypes.get(2)),
            new Activity("making my SI assignment",userTypes.get(2)),
            new Activity("having 1 on 1 conversation",userTypes.get(1)),
            new Activity("hosting QualityGate",userTypes.get(1)),
            new Activity("having code review",userTypes.get(1))
        ));
        activityRepository.saveAll(activities);


        reservationRepository.save(new Reservation(1, 2, ZonedDateTime.now(), ZonedDateTime.now(),1));
        reservationRepository.save(new Reservation(2, 1, ZonedDateTime.now(), ZonedDateTime.now(),1));
        reservationRepository.save(new Reservation(3, 2, ZonedDateTime.now(), ZonedDateTime.now(),1));
        reservationRepository.save(new Reservation(4, 3, ZonedDateTime.now(), ZonedDateTime.now(),1));
    }
}
