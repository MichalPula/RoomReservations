package com.Pulson.RoomReservations.services.mappers;

import com.Pulson.RoomReservations.entities.Reservation;
import com.Pulson.RoomReservations.entities.dtos.ReservationReadDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReadReservationMapper {

    public List<ReservationReadDTO> mapToReservationReadDTOsList(List<Reservation> reservations){
        List<ReservationReadDTO> reservationReadDTOList = new ArrayList<>();

        reservations.forEach(reservation -> {
            ReservationReadDTO reservationReadDTO = new ReservationReadDTO();
            reservationReadDTO.setFirstName(reservation.getUser().getFirstName());
            reservationReadDTO.setLastName(reservation.getUser().getLastName());
            reservationReadDTO.setRoomName(reservation.getRoom().getName());
            reservationReadDTO.setStartTime(reservation.getStartTime());
            reservationReadDTO.setEndTime(reservation.getEndTime());
            reservationReadDTO.setActivityName(reservation.getActivity().getName());

            reservationReadDTOList.add(reservationReadDTO);
        });
        return reservationReadDTOList;
    }
}
