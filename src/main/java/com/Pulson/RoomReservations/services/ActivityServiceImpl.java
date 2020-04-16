package com.Pulson.RoomReservations.services;

import com.Pulson.RoomReservations.entities.Activity;
import com.Pulson.RoomReservations.repositories.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @PersistenceContext
    private EntityManager em;

    private ActivityRepository activityRepository;

    @Autowired
    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public List<Activity> getAll() {
        return activityRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public Activity getById(long id) throws Exception {
        return activityRepository.findById(id).orElseThrow(() -> new Exception("Activity " + id + " NOT found"));
    }

    @Transactional
    @Override
    public Boolean create(Activity activity) {
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
        activity.setName(activityDetails.getName());
        activity.setAuthorities(activityDetails.getAuthorities());
        activity.setAvailable(activityDetails.getAvailable());
        activityRepository.save(activity);
        return true;
    }
}
