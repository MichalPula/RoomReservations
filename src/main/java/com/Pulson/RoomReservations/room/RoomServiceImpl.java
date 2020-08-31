package com.Pulson.RoomReservations.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @PersistenceContext
    private EntityManager em;

    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<Room> getAll() {
        return roomRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public Room getById(long id) {
        return roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundException(id));
    }

    @Transactional
    @Override
    public String create(Room room) {
        roomRepository.save(room);
        return "Room added";
    }

    @Transactional
    @Override
    public String deactivate(long id) {
        Query query = em.createNativeQuery("update rooms set is_available = false where id = ?");
        query.setParameter(1, id);
        query.executeUpdate();
        return "Room deactivated";
    }

    @Transactional
    @Override
    public String update(long id, Room roomDetails) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundException(id));
        room.setName(roomDetails.getName());
        room.setAvailable(roomDetails.getAvailable());
        roomRepository.save(room);
        return "Room updated";
    }
}
