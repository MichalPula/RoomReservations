package com.Pulson.RoomReservations.services;

import com.Pulson.RoomReservations.entities.Reservation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReservationService {
    List<Reservation> getAll();
    List<Reservation> getActive();
    List<Reservation> getActiveByUser(long userId) throws Exception;
    List<Reservation> getHistoryByUser(long userId) throws Exception;
    List<Integer> getStartingHoursListByDateByRoom(int year, int month, int day, long roomId) throws Exception;
    Integer getAmountByDateByUser(int year, int month, int day, long userId) throws Exception;
    Reservation getById(long id) throws Exception;
    Boolean create(Reservation reservation) throws Exception;
    Boolean update(long id, Reservation reservation) throws Exception;
    Boolean delete(long id) throws Exception;
}
