package com.Pulson.RoomReservations.services;

import com.Pulson.RoomReservations.entities.User;
import com.Pulson.RoomReservations.models.BasicAccountDataChangeRequest;
import com.Pulson.RoomReservations.models.EmailChangeRequest;
import com.Pulson.RoomReservations.models.PasswordChangeRequest;
import com.Pulson.RoomReservations.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private EntityManager entityManager;
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(EntityManager entityManager, UserRepository userRepository) {
        this.entityManager = entityManager;
        this.userRepository = userRepository;
    }

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
        userRepository.save(user);
        return true;
    }

    @Transactional
    @Override
    public Boolean deactivate(long id) {
        Query query = entityManager.createNativeQuery("update users set is_active = false where id = ?");
        query.setParameter(1, id);
        query.executeUpdate();
        return true;
    }

    @Transactional
    @Override
    public Boolean updateBasicInfo(BasicAccountDataChangeRequest basicAccountInfo) throws Exception {
        User user = userRepository.findById(basicAccountInfo.getUserId()).orElseThrow(() -> new Exception("User NOT found"));
        user.setFirstName(basicAccountInfo.getFirstName());
        user.setLastName(basicAccountInfo.getLastName());
        user.setPhoneNumber(basicAccountInfo.getPhoneNumber());
        userRepository.save(user);
        return true;
    }

    @Override
    public Boolean updateEmail(EmailChangeRequest emailChangeRequest) throws Exception {
        User user = userRepository.findById(emailChangeRequest.getUserId()).orElseThrow(() -> new Exception("User NOT found"));
        user.setUsername(emailChangeRequest.getEmail());
        userRepository.save(user);
        return true;
    }

    @Override
    public ResponseEntity<?> updatePassword(PasswordChangeRequest passwordChangeRequest) throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = userRepository.findById(passwordChangeRequest.getUserId()).orElseThrow(() -> new Exception("User NOT found"));
        if(!bCryptPasswordEncoder.matches(passwordChangeRequest.getOldPassword(), user.getPassword())){
            return new ResponseEntity<>("Wrong password", HttpStatus.CONFLICT);
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(passwordChangeRequest.getNewPassword()));
            userRepository.save(user);
        }
        return new ResponseEntity<>("Password changed", HttpStatus.OK);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public BasicAccountDataChangeRequest getBasicAccountData(long userId) {
        Query query = entityManager.createNativeQuery("select" +
                " first_name, last_name, phone_number" +
                " from users" +
                " where id = ?");
        query.setParameter(1, userId);
        List<Object[]> resultList = query.getResultList();
        return new BasicAccountDataChangeRequest(resultList.get(0)[0].toString(),
                resultList.get(0)[1].toString(), Long.parseLong(resultList.get(0)[2].toString()));
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
