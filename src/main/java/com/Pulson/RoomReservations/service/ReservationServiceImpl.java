package com.Pulson.RoomReservations.service;

import com.Pulson.RoomReservations.model.Reservation;
import com.Pulson.RoomReservations.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService{

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getById(long id) throws Exception {
        return reservationRepository.findById(id).orElseThrow(() -> new Exception("Reservation " + id + " NOT found"));
    }

}
