package com.example.GameStopGradsProject.service;

import com.example.GameStopGradsProject.model.User;
import com.example.GameStopGradsProject.repository.UserRepository;
import com.example.GameStopGradsProject.security.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JpaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> u = userRepository.findUserByUsername(username);
        return u.map(SecurityUser::new).orElseThrow(
                () -> new UsernameNotFoundException("Username not found:" + username)
        );
    }
}
