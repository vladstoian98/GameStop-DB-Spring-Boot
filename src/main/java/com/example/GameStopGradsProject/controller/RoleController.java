package com.example.GameStopGradsProject.controller;

import com.example.GameStopGradsProject.exception.IdDoesNotExist;
import com.example.GameStopGradsProject.model.Role;
import com.example.GameStopGradsProject.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/roles")
@AllArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<Role> create(
            @RequestBody Role role) {
        Role createdRole = roleService.create(role);

        return ResponseEntity
                .created(URI.create("/roles" + ""))
                .body(createdRole);
    }

    @DeleteMapping("/deletion/{id}")
    public ResponseEntity<?> deleteRoleById(@PathVariable Long id) {
        try {
            roleService.deleteRoleById(id);

            return ResponseEntity
                    .ok()
                    .build();
        }
        catch (IdDoesNotExist e) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }
}
