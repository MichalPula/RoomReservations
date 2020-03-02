package com.Pulson.RoomReservations.services.mappers;

import com.Pulson.RoomReservations.entities.Activity;
import com.Pulson.RoomReservations.entities.Reservation;
import com.Pulson.RoomReservations.entities.Room;
import com.Pulson.RoomReservations.entities.User;
import com.Pulson.RoomReservations.entities.dtos.ReservationCreateUpdateDTO;
import com.Pulson.RoomReservations.repositories.ActivityRepository;
import com.Pulson.RoomReservations.repositories.RoomRepository;
import com.Pulson.RoomReservations.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateUpdateReservationMapper {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ActivityRepository activityRepository;

    public Reservation mapToReservation(ReservationCreateUpdateDTO reservationAddDTO) throws Exception {
        Reservation reservation = new Reservation();
        User user = userRepository.findById(reservationAddDTO.getUserId()).orElseThrow(()-> new Exception("User NOT found"));
        Room room = roomRepository.findById(reservationAddDTO.getRoomId()).orElseThrow(() -> new Exception("Room NOT found"));
        Activity activity = activityRepository.findById(reservationAddDTO.getActivityId()).orElseThrow(() -> new Exception("Activity NOT found"));
        reservation.setUser(user);
        reservation.setRoom(room);
        reservation.setStartTime(reservationAddDTO.getStartTime());
        reservation.setEndTime(reservationAddDTO.getEndTime());
        reservation.setActivity(activity);
        return reservation;
    }
}
