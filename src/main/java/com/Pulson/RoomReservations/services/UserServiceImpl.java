package com.Pulson.RoomReservations.services;

import com.Pulson.RoomReservations.entities.User;
import com.Pulson.RoomReservations.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(long id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new Exception("User " + id + " NOT found"));
    }

    @Transactional
    @Override
    public Boolean create(User user) {
        Role role = userTypeRepository.findByName(user.getRole().getName());
        user.setRole(role);
        userRepository.save(user);
        return true;
    }

    @Transactional
    @Override
    public Boolean deactivate(long id) {
        Query query = em.createNativeQuery("update users set is_active = false where id = ?");
        query.setParameter(1, id);
        query.executeUpdate();
        return true;
    }

    @Transactional
    @Override
    public Boolean update(long id, User userDetails) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() -> new Exception("User NOT found"));
        user.setEMail(userDetails.getEMail());
        user.setNickName(userDetails.getNickName());
        user.setActive(userDetails.getActive());
        userRepository.save(user);
        return true;
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            User user = userRepository.getUserByUsername(username);
            return UserDetailsImpl.build(user);
        }catch (Exception e){
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }
    }
}
