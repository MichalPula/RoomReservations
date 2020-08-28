package com.Pulson.RoomReservations.reservation;

import com.Pulson.RoomReservations.activity.Activity;
import com.Pulson.RoomReservations.activity.ActivityRepository;
import com.Pulson.RoomReservations.room.Room;
import com.Pulson.RoomReservations.room.RoomRepository;
import com.Pulson.RoomReservations.user.User;
import com.Pulson.RoomReservations.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class CreateUpdateReservationMapper {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final ActivityRepository activityRepository;

    @Autowired
    public CreateUpdateReservationMapper(UserRepository userRepository, RoomRepository roomRepository, ActivityRepository activityRepository) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.activityRepository = activityRepository;
    }

    public Reservation mapToReservation(ReservationCreateUpdateDTO reservationCreateUpdateDTO) throws Exception {
        Reservation reservation = new Reservation();
        User user = userRepository.findById(reservationCreateUpdateDTO.getUserId()).orElseThrow(() -> new Exception("User NOT found"));
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
