package com.Pulson.RoomReservations.service;

import com.Pulson.RoomReservations.model.Room;
import com.Pulson.RoomReservations.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class RoomServiceImpl implements RoomService{

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private RoomRepository repository;

    @Override
    public List<Room> getAll() {
        return repository.findAll();
    }
}
