package com.Pulson.RoomReservations.service;

import com.Pulson.RoomReservations.model.*;
import com.Pulson.RoomReservations.repository.ActivityRepository;
import com.Pulson.RoomReservations.repository.ReservationRepository;
import com.Pulson.RoomReservations.repository.RoomRepository;
import com.Pulson.RoomReservations.repository.UserRepository;
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

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public List<Reservation> getAll() {
        return reservationRepository.findAll();
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
