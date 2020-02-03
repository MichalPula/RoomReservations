package com.Pulson.RoomReservations.service;

import com.Pulson.RoomReservations.model.Reservation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReservationService {
    List<Reservation> getAll();
    Reservation getById(long id) throws Exception;
    Boolean create(Reservation reservation) throws Exception;
    Boolean delete(long id) throws Exception;
}
