package com.Pulson.RoomReservations.service;

import com.Pulson.RoomReservations.model.Room;
import com.Pulson.RoomReservations.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

    @Transactional
    @Override
    public Boolean create(Room room) {
        repository.save(room);
        return true;
    }

    @Transactional
    @Override
    public Boolean deactivate(long id) throws Exception {
        Query query = em.createNativeQuery("update rooms set is_available = false where id = ?");
        query.setParameter(1, id);
        query.executeUpdate();
        return true;
    }

    @Transactional
    @Override
    public Boolean update(long id, Room roomDetails) throws Exception {
        Room room = repository.findById(id).orElseThrow(() -> new Exception("Room NOT found"));
        room.setName(roomDetails.getName());
        room.setAvailable(roomDetails.getAvailable());
        repository.save(room);
        return true;
    }
}
