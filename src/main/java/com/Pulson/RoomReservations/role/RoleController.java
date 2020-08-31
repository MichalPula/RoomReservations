package com.Pulson.RoomReservations.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("roles")
public class RoleController {

    private final RoleService roleService;
    private final ReadRoleMapper readRoleMapper;

    @Autowired
    public RoleController(RoleService roleService, ReadRoleMapper readRoleMapper) {
        this.roleService = roleService;
        this.readRoleMapper = readRoleMapper;
    }

    @GetMapping("/all")
    public ResponseEntity<List<RoleReadDTO>> getAll() {
        return ResponseEntity.ok().body(readRoleMapper.mapToRoleReadDTOsList(roleService.getAll()));
    }
}
