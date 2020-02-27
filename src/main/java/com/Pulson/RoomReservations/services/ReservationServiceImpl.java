package com.Pulson.RoomReservations.services;

import com.Pulson.RoomReservations.entities.*;
import com.Pulson.RoomReservations.repositories.ActivityRepository;
import com.Pulson.RoomReservations.repositories.ReservationRepository;
import com.Pulson.RoomReservations.repositories.RoomRepository;
import com.Pulson.RoomReservations.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService{

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ActivityRepository activityRepository;

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

    @Transactional
    @Override
    public Boolean create(Reservation reservation) throws Exception {
        long userId = reservation.getUser().getId();
        long roomId = reservation.getRoom().getId();
        long activityId = reservation.getActivity().getId();
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("User " + userId + " NOT found"));
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new Exception("Room " + roomId + " NOT found"));
        Activity activity = activityRepository.findById(activityId).orElseThrow(() -> new Exception("Activity " + activityId + " NOT found"));
        reservation.setUser(user);
        reservation.setRoom(room);
        reservation.setActivity(activity);
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
