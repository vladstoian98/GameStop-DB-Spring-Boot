package com.example.GameStopGradsProject.service;

import com.example.GameStopGradsProject.model.Role;
import com.example.GameStopGradsProject.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public Role create(Role role) {
        return roleRepository.save(role);
    }


}
