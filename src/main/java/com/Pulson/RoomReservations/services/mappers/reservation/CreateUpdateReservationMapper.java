package com.Pulson.RoomReservations.services.mappers.reservation;

import com.Pulson.RoomReservations.entities.Activity;
import com.Pulson.RoomReservations.entities.Reservation;
import com.Pulson.RoomReservations.entities.Room;
import com.Pulson.RoomReservations.entities.User;
import com.Pulson.RoomReservations.entities.dtos.reservation.ReservationCreateUpdateDTO;
import com.Pulson.RoomReservations.repositories.ActivityRepository;
import com.Pulson.RoomReservations.repositories.RoomRepository;
import com.Pulson.RoomReservations.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class CreateUpdateReservationMapper {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ActivityRepository activityRepository;

    public Reservation mapToReservation(ReservationCreateUpdateDTO reservationCreateUpdateDTO) throws Exception {
        Reservation reservation = new Reservation();
        User user = userRepository.findById(reservationCreateUpdateDTO.getUserId()).orElseThrow(()-> new Exception("User NOT found"));
        reservation.setUser(user);
        Room room = roomRepository.findByName(reservationCreateUpdateDTO.getRoomName());
        reservation.setRoom(room);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        reservation.setStartTime(LocalDateTime.parse(reservationCreateUpdateDTO.getStartTime(), formatter));
        reservation.setEndTime(LocalDateTime.parse(reservationCreateUpdateDTO.getEndTime(), formatter));

        Activity activity = activityRepository.findByName(reservationCreateUpdateDTO.getActivityName());
        reservation.setActivity(activity);
        return reservation;
    }
}
