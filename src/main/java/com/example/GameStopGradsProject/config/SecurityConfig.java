package com.example.GameStopGradsProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .mvcMatchers("/gamecharacters").hasAuthority("write")
                .mvcMatchers("/employees").hasAuthority("write")
                .mvcMatchers("/gameconsoles").hasAuthority("write")
                .mvcMatchers("/gamestopshops").hasAuthority("write")
                .mvcMatchers("/videogames").hasAuthority("write")
                .mvcMatchers("/employees/deletion/{id}").hasAuthority("write")
                .mvcMatchers("/gamecharacters/deletion/{id}").hasAuthority("write")
                .mvcMatchers("/gameconsoles/deletion/{id}").hasAuthority("write")
                .mvcMatchers("/videogames/deletion/{id}").hasAuthority("write")
                .mvcMatchers("/gamestopshops/deletion/{id}").hasAuthority("write")
                .mvcMatchers("/employees/shopassignation/{employeeId}/{gameStopShopId}").hasAuthority("write")
                .mvcMatchers("/videogames/{videoGameId}/{gameCharacterId}").hasAuthority("write")
                .mvcMatchers("/gamestopshops/bothwaysconsole/{gameStopShopId}/{gameConsoleId}").hasAuthority("write")
                .mvcMatchers("/gamestopshops/bothwaysgame/{gameStopShopId}/{videoGameId}").hasAuthority("write")
                .mvcMatchers("/gamecharacters/{id}").hasAuthority("read")
                .mvcMatchers("/employees/{id}").hasAuthority("read")
                .mvcMatchers("/gameconsoles/{id}").hasAuthority("read")
                .mvcMatchers("/gamestopshops/{id}").hasAuthority("read")
                .mvcMatchers("/videogames/{id}").hasAuthority("read")
                .mvcMatchers("/users/**").permitAll()
                .mvcMatchers("/roles/**").permitAll()
                .and()
                .csrf().disable()
                .build();
    }
}
