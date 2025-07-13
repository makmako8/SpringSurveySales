package com.example.springsurveysales.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.springsurveysales.entity.User;
import com.example.springsurveysales.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
   @Autowired
    private UserRepository userRepository;

   @Bean
   public UserDetailsService userDetailsService() {
       return username -> {
           User user = userRepository.findByUsername(username)
               .orElseThrow(() -> new UsernameNotFoundException("User not found"));

           return new org.springframework.security.core.userdetails.User(
                   user.getUsername(),
                   user.getPassword(),
                   Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
               );
       };
   }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/register", "/login", "/css/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(login -> login
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/form", true)
            )
            .logout(logout -> logout.permitAll());

        return http.build();
    }
}
