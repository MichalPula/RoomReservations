package com.Pulson.RoomReservations.services;

import com.Pulson.RoomReservations.entities.*;
import com.Pulson.RoomReservations.repositories.ReservationRepository;
import com.Pulson.RoomReservations.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService{

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> getByUser(long userId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("User " + userId + " NOT found"));
        return reservationRepository.findAllByUser(user);
    }

    @Override
    public Reservation getById(long id) throws Exception {
        return reservationRepository.findById(id).orElseThrow(() -> new Exception("Reservation " + id + " NOT found"));
    }

    @Override
    @Transactional
    public Boolean create(Reservation reservation) throws Exception {
        reservationRepository.save(reservation);
        return true;
    }

    @Override
    @Transactional
    public Boolean update(long id, Reservation reservationDetails) throws Exception {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new Exception("Reservation NOT found"));
        reservation.setUser(reservationDetails.getUser());
        reservation.setRoom(reservationDetails.getRoom());
        reservation.setStartTime(reservationDetails.getStartTime());
        reservation.setEndTime(reservationDetails.getEndTime());
        reservation.setActivity(reservationDetails.getActivity());
        reservationRepository.save(reservation);
        return true;
    }

    @Override
    @Transactional
    public Boolean delete(long id) {
        Query query = em.createNativeQuery("delete from reservations where id = ?");
        query.setParameter(1, id);
        query.executeUpdate();
        return true;
    }
}
