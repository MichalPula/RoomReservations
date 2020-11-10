package com.Pulson.RoomReservations.activity;

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

    @Transactional
    @Override
    public String create(Activity activity) {
        activityRepository.save(activity);
        return "Activity created";
    }

    @Override
    @Transactional
    public String deactivate(long id) {
        Query query = em.createNativeQuery("update activities set is_available = false where id = ?");
        query.setParameter(1, id);
        query.executeUpdate();
        return "Activity deactivated";
    }

    @Transactional
    @Override
    public String update(long id, Activity activityDetails) {
        Activity activity = activityRepository.findById(id).orElseThrow(() -> new ActivityNotFoundException(id));
        activity.setName(activityDetails.getName());
        activity.setAuthorities(activityDetails.getAuthorities());
        activity.setAvailable(activityDetails.getAvailable());
        activityRepository.save(activity);
        return "Activity updated";
    }
}
