package com.example.GameStopGradsProject.controller;

import com.example.GameStopGradsProject.model.Role;
import com.example.GameStopGradsProject.service.RoleService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<Role> create(
            @RequestBody Role role) {

        Role createdRole = roleService.create(role);

        return ResponseEntity
                .created(URI.create("/roles" + ""))
                .body(createdRole);
    }
}
