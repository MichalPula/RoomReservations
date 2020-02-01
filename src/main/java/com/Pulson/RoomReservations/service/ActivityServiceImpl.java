package com.Pulson.RoomReservations.service;

import com.Pulson.RoomReservations.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class ActivityServiceImpl {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private ActivityRepository repository;
}
