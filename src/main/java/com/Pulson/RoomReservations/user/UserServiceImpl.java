package com.Pulson.RoomReservations.user;

import com.Pulson.RoomReservations.authentication.account_data_change.BasicAccountDataChangeRequest;
import com.Pulson.RoomReservations.authentication.account_data_change.EmailChangeRequest;
import com.Pulson.RoomReservations.authentication.account_data_change.PasswordChangeRequest;
import com.Pulson.RoomReservations.role.Role;
import com.Pulson.RoomReservations.role.RoleRepository;
import com.Pulson.RoomReservations.role.RoleType;
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
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final EntityManager entityManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(EntityManager entityManager, UserRepository userRepository, RoleRepository roleRepository) {
        this.entityManager = entityManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> getAllStudents() {
        Role admin = roleRepository.findByRoleType(RoleType.ROLE_ADMIN);
        return userRepository.findAllByRolesNotContaining(admin);
    }

    @Override
    public List<User> getStudentByName(String firstName, String lastName) {
        List<User> studentsMatchingConstraint = new ArrayList<>();
        Role admin = roleRepository.findByRoleType(RoleType.ROLE_ADMIN);
        if (!firstName.equals("null") && !lastName.equals("null")) {
            return userRepository.findAllByRolesNotContainingAndFirstNameIgnoreCaseAndLastNameIgnoreCase
                    (admin, firstName, lastName);
        } else {
            List<User> students = userRepository.findAllByRolesNotContaining(admin);
            students.forEach(student -> {
                if (student.getFirstName().toLowerCase().equals(firstName.toLowerCase()) ||
                        student.getLastName().toLowerCase().equals(firstName.toLowerCase())) {
                    studentsMatchingConstraint.add(student);
                }
            });
        }
        return studentsMatchingConstraint;
    }

    @Transactional
    @Override
    public Boolean create(User user) {
        userRepository.save(user);
        return true;
    }

    @Transactional
    @Override
    public Boolean updateBasicInfo(BasicAccountDataChangeRequest basicAccountInfo) {
        User user = userRepository.getOne(basicAccountInfo.getUserId());
        user.setFirstName(basicAccountInfo.getFirstName());
        user.setLastName(basicAccountInfo.getLastName());
        user.setPhoneNumber(basicAccountInfo.getPhoneNumber());
        userRepository.save(user);
        return true;
    }

    @Override
    public ResponseEntity<?> updateEmail(EmailChangeRequest emailChangeRequest) {
        if (userRepository.existsByUsername(emailChangeRequest.getEmail())) {
            return new ResponseEntity<>("Username is taken", HttpStatus.CONFLICT);
        } else {
            User user = userRepository.getOne(emailChangeRequest.getUserId());
            user.setUsername(emailChangeRequest.getEmail());
            userRepository.save(user);
        }
        return ResponseEntity.ok().body("Username changed");
    }

    @Override
    public ResponseEntity<?> updatePassword(PasswordChangeRequest passwordChangeRequest) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = userRepository.getOne(passwordChangeRequest.getUserId());
        if (!bCryptPasswordEncoder.matches(passwordChangeRequest.getOldPassword(), user.getPassword())) {
            return new ResponseEntity<>("Wrong password", HttpStatus.CONFLICT);
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(passwordChangeRequest.getNewPassword()));
            userRepository.save(user);
        }
        return ResponseEntity.ok().body("Password changed");
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public UserBasicAccountData getBasicAccountData(long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException(userId));
        return new UserBasicAccountData(user.getFirstName(), user.getLastName(), user.getPhoneNumber());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userRepository.getUserByUsername(username);
            return UserDetailsImpl.build(user);
        } catch (Exception e) {
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }
    }
}
