package com.example.GameStopGradsProject.service;

import com.example.GameStopGradsProject.exception.IdDoesNotExist;
import com.example.GameStopGradsProject.model.Role;
import com.example.GameStopGradsProject.model.User;
import com.example.GameStopGradsProject.repository.RoleRepository;
import com.example.GameStopGradsProject.repository.UserRepository;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

//        user.getRoles().forEach(role -> {
//            Set<User> users = new HashSet<>();
//            users.add(user);
//            role.setUsers(users);
//        });
//
//        roleRepository.saveAll(user.getRoles());
        return userRepository.save(user);
    }

    @Transactional
    public void assignRole(long userId, long roleId) {
        Optional<User> foundUser = userRepository.findUserById(userId);
        Optional<Role> foundRole = roleRepository.findRoleById(roleId);

        if(foundUser.isEmpty())
            throw new IdDoesNotExist("User", userId);
        else if(foundRole.isEmpty())
            throw new IdDoesNotExist("Role", roleId);
        else {
            foundUser.get().getRoles().add(foundRole.get());
            foundRole.get().getUsers().add(foundUser.get());
        }
    }
}
