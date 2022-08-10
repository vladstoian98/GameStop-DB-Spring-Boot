package com.example.GameStopGradsProject.security;

import com.example.GameStopGradsProject.model.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class SecurityRole implements GrantedAuthority {

    private final Role role;

    @Override
    public String getAuthority() {
        return role.getName();
    }
}
