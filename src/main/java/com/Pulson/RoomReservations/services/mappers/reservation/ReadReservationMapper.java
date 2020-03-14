package com.Pulson.RoomReservations.services.mappers.reservation;

import com.Pulson.RoomReservations.entities.Reservation;
import com.Pulson.RoomReservations.entities.dtos.reservation.ReservationReadDTO;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReadReservationMapper {

    public List<ReservationReadDTO> mapToReservationReadDTOsList(List<Reservation> reservations){
        List<ReservationReadDTO> reservationReadDTOList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        reservations.forEach(reservation -> {
            ReservationReadDTO reservationReadDTO = new ReservationReadDTO();
            reservationReadDTO.setId(reservation.getId());
            reservationReadDTO.setFirstName(reservation.getUser().getFirstName());
            reservationReadDTO.setLastName(reservation.getUser().getLastName());
            reservationReadDTO.setRoomName(reservation.getRoom().getName());
            reservationReadDTO.setStartTime(reservation.getStartTime().format(formatter));
            reservationReadDTO.setEndTime(reservation.getEndTime().format(formatter));
            reservationReadDTO.setActivityName(reservation.getActivity().getName());

            reservationReadDTOList.add(reservationReadDTO);
        });
        return reservationReadDTOList;
    }
}
