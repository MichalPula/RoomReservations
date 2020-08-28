package com.Pulson.RoomReservations.reservation;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReservationService {
    List<Reservation> getAll();

    List<Reservation> getActive();

    List<Reservation> getActiveByUser(long userId) throws Exception;

    List<Reservation> getHistoryByUser(long userId) throws Exception;

    List<Reservation> getByDate(int year, int month, int day);

    List<Integer> getStartingHoursListByDateByRoom(int year, int month, int day, long roomId);

    Integer getAmountByDateByUser(int year, int month, int day, long userId) throws Exception;

    Reservation getById(long id) throws Exception;

    Boolean create(Reservation reservation);

    Boolean update(long id, Reservation reservation) throws Exception;

    Boolean delete(long id);
}
