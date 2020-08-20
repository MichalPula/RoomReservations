package com.Pulson.RoomReservations.services;

import com.Pulson.RoomReservations.Role.RoleService;
import com.Pulson.RoomReservations.User.UserDetailsImpl;
import com.Pulson.RoomReservations.User.UserService;
import com.Pulson.RoomReservations.Role.Role;
import com.Pulson.RoomReservations.Role.RoleType;
import com.Pulson.RoomReservations.User.User;
import com.Pulson.RoomReservations.models.JwtLoginRequest;
import com.Pulson.RoomReservations.models.JwtRegisterRequest;
import com.Pulson.RoomReservations.models.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AuthenticationServiceImpl implements AuthenticationService {

    private AuthenticationManager authenticationManager;
    private JwtTokenService jwtTokenService;
    private UserService userService;
    private RoleService roleService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, JwtTokenService jwtTokenService,
                                     UserService userService, RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.userService = userService;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public ResponseEntity<?> handleLogin(JwtLoginRequest jwtLoginRequest) throws Exception {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(jwtLoginRequest.getUsername(), jwtLoginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String token = jwtTokenService.generateToken(userDetails);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(
                token,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }

    @Override
    public ResponseEntity<?> handleRegistration(JwtRegisterRequest jwtRegisterRequest) {
        if (userService.existsByUsername(jwtRegisterRequest.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken!");
        }

        Set<String> stringRoles = jwtRegisterRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (stringRoles == null) {
            Role userRole = roleService.findByRoleType(RoleType.ROLE_USER);
            roles.add(userRole);
        } else {
            stringRoles.forEach(role -> {
                if ("admin".equals(role)) {
                    roles.add(roleService.findByRoleType(RoleType.ROLE_ADMIN));
                    roles.add(roleService.findByRoleType(RoleType.ROLE_USER));
                }
            });
        }
        User user = new User(jwtRegisterRequest.getFirstName(), jwtRegisterRequest.getLastName(), jwtRegisterRequest.getPhoneNumber(),
                jwtRegisterRequest.getUsername(), bCryptPasswordEncoder.encode(jwtRegisterRequest.getPassword()), roles);
        userService.create(user);

        return ResponseEntity.ok("Registration successful");
    }
}
