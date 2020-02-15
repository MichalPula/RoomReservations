package com.Pulson.RoomReservations.services;

import com.Pulson.RoomReservations.entities.Activity;
import com.Pulson.RoomReservations.repositories.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService{

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public List<Activity> getAll() {
        return activityRepository.findAll();
    }

    @Override
    public Activity getById(long id) throws Exception {
        return activityRepository.findById(id).orElseThrow(() -> new Exception("Activity " + id + " NOT found"));
    }

    @Transactional
    @Override
    public Boolean create(Activity activity) {
        //Role role = userTypeRepository.findByName(activity.getRole().getName());
        //activity.setRole(role);
        activityRepository.save(activity);
        return true;
    }

    @Override
    @Transactional
    public Boolean deactivate(long id) {
        Query query = em.createNativeQuery("update activities set is_available = false where id = ?");
        query.setParameter(1, id);
        query.executeUpdate();
        return true;
    }

    @Transactional
    @Override
    public Boolean update(long id, Activity activityDetails) throws Exception {
        Activity activity = activityRepository.findById(id).orElseThrow(() -> new Exception("Activity NOT found"));
        //Role role = userTypeRepository.findByName(activityDetails.getRole().getName());
        //activity.setRole(role);
        activity.setName(activityDetails.getName());
        activity.setAvailable(activityDetails.getAvailable());
        activityRepository.save(activity);
        return true;
    }
}
