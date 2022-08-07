package com.example.GameStopGradsProject.repository;

import com.example.GameStopGradsProject.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findRoleById(Long id);
}
