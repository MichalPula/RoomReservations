package com.Pulson.RoomReservations.service;

import com.Pulson.RoomReservations.model.Activity;
import com.Pulson.RoomReservations.model.UserType;
import com.Pulson.RoomReservations.repository.ActivityRepository;
import com.Pulson.RoomReservations.repository.UserTypeRepository;
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

    @Autowired
    private UserTypeRepository userTypeRepository;

    @Override
    public List<Activity> getAll() {
        return activityRepository.findAll();
    }

    @Override
    public Activity getById(long id) throws Exception {
        return activityRepository.findById(id).orElseThrow(() -> new Exception("Activity " + id + " not found"));
    }

    @Transactional
    @Override
    public Boolean create(Activity activity) {
        UserType userType = userTypeRepository.findByName(activity.getUserType().getName());
        activity.setUserType(userType);
        activityRepository.save(activity);
        return true;
    }

    @Override
    @Transactional
    public Boolean deactivate(long id) throws Exception {
        Query query = em.createNativeQuery("update activities set is_available = false where id = ?");
        query.setParameter(1, id);
        query.executeUpdate();
        return true;
    }

    @Transactional
    @Override
    public Boolean update(long id, Activity activityDetails) throws Exception {
        Activity activity = activityRepository.findById(id).orElseThrow(() -> new Exception("Activity NOT found"));
        UserType userType = userTypeRepository.findByName(activityDetails.getUserType().getName());
        activity.setUserType(userType);
        activity.setName(activityDetails.getName());
        activity.setAvailable(activityDetails.getAvailable());
        activityRepository.save(activity);
        return true;
    }
}
