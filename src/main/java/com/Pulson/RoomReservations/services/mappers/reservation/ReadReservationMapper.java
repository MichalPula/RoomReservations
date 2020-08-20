package com.Pulson.RoomReservations.services.mappers.reservation;

import com.Pulson.RoomReservations.Reservation.Reservation;
import com.Pulson.RoomReservations.Reservation.MarkedReservation;
import com.Pulson.RoomReservations.Reservation.ReservationReadDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReadReservationMapper {

    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final String RED = "red";
    private final String GREEN = "green";

    public List<ReservationReadDTO> mapToReservationReadDTOsList(List<Reservation> reservations) {
        List<ReservationReadDTO> reservationReadDTOList = new ArrayList<>();

        reservations.forEach(reservation -> {
            ReservationReadDTO reservationReadDTO = new ReservationReadDTO();
            reservationReadDTO.setId(reservation.getId());
            reservationReadDTO.setFirstName(reservation.getUser().getFirstName());
            reservationReadDTO.setLastName(reservation.getUser().getLastName());
            reservationReadDTO.setRoomName(reservation.getRoom().getName());
            reservationReadDTO.setStartTime(reservation.getStartTime().format(FORMATTER));
            reservationReadDTO.setEndTime(reservation.getEndTime().format(FORMATTER));
            reservationReadDTO.setActivityName(reservation.getActivity().getName());

            reservationReadDTOList.add(reservationReadDTO);
        });
        return reservationReadDTOList;
    }

    public List<MarkedReservation> mapToMarkedReservationsList(List<ReservationReadDTO> reservationsDTOsList) {
        List<MarkedReservation> markedReservations = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        reservationsDTOsList.forEach(reservationReadDTO -> {
            MarkedReservation markedReservation = new MarkedReservation();
            if (LocalDateTime.parse(reservationReadDTO.getStartTime(), FORMATTER).isBefore(now)) {
                markedReservation.setColor(RED);
            } else {
                markedReservation.setColor(GREEN);
            }
            markedReservation.setReservation(reservationReadDTO);
            markedReservations.add(markedReservation);
        });
        return markedReservations;
    }
}
