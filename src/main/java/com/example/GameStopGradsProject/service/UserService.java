package com.example.GameStopGradsProject.service;

import com.example.GameStopGradsProject.exception.IdDoesNotExist;
import com.example.GameStopGradsProject.model.Role;
import com.example.GameStopGradsProject.model.User;
import com.example.GameStopGradsProject.repository.RoleRepository;
import com.example.GameStopGradsProject.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Transactional
    public void assignBothWays(long userId, long roleId) {
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

    @Transactional
    public void deleteUserById(Long id) {
        var selectedUser = userRepository.findUserById(id);

        if(selectedUser.isEmpty())
            throw new IdDoesNotExist("User", id);
        else {
            userRepository.deleteUserById(id);
        }
    }
}
