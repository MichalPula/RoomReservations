package com.Pulson.RoomReservations.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("roles")
public class RoleController {

    private RoleService roleService;
    private ReadRoleMapper readRoleMapper;

    @Autowired
    public RoleController(RoleService roleService, ReadRoleMapper readRoleMapper) {
        this.roleService = roleService;
        this.readRoleMapper = readRoleMapper;
    }

    @GetMapping("/all")
    public List<RoleReadDTO> getAll() {
        return readRoleMapper.mapToRoleReadDTOsList(roleService.getAll());
    }
}
