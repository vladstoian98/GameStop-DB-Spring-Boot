package com.example.GameStopGradsProject.service;

import com.example.GameStopGradsProject.exception.IdDoesNotExist;
import com.example.GameStopGradsProject.model.Role;
import com.example.GameStopGradsProject.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    @Transactional
    public Role create(Role role) {
        return roleRepository.save(role);
    }

    @Transactional
    public void deleteRoleById(Long id) {
        var selectedRole = roleRepository.findRoleById(id);

        if(selectedRole.isEmpty())
            throw new IdDoesNotExist("Employee", id);
        else {
            roleRepository.deleteRoleById(id);
        }
    }


}
