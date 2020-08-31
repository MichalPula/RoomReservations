package com.Pulson.RoomReservations.reservation;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReservationService {

    List<Reservation> getActive();

    List<Reservation> getActiveByUser(long userId);

    List<Reservation> getHistoryByUser(long userId);

    List<Reservation> getByDate(int year, int month, int day);

    List<Integer> getStartingHoursListByDateByRoom(int year, int month, int day, long roomId);

    Integer getAmountByDateByUser(int year, int month, int day, long userId);

    String create(Reservation reservation);

    String delete(long id);
}
