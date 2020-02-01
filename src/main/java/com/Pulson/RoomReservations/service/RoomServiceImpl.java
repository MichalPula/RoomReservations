package com.Pulson.RoomReservations.service;

import com.Pulson.RoomReservations.model.Room;
import com.Pulson.RoomReservations.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService{

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private RoomRepository repository;

    @Override
    public List<Room> getAll() {
        return repository.findAll();
    }

    @Override
    public Room getById(long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("User " + id + " not found"));
    }

    @Override
    public Boolean create(Room room) {
        repository.save(room);
        return true;
    }

    @Override
    public Boolean delete(long id) throws Exception {
        repository.delete(repository.findById(id).orElseThrow(() -> new Exception("Room has NOT been removed")));
        return true;
    }
}
