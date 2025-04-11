package com.aouth.service;

import com.aouth.dto.UserRegistrationDto;
import com.aouth.entity.AppUser;
import com.aouth.entity.Role;
import com.aouth.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
 
@Service
public class AppUserDetailsService implements UserDetailsService {
 
    private PasswordEncoder passwordEncoder;
 
    private final AppUserRepository userRepository;
 
    public AppUserDetailsService(AppUserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
 
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
 
        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().name()) // Must match Spring roles like "USER", "ADMIN"
                .build();
    }
 
    public boolean existsByUsernameOrEmail(String username, String email) {
        return userRepository.existsByUsername(username) || userRepository.existsByEmail(email);
    }
 
    public void registerNewUser(UserRegistrationDto dto) {
        AppUser user = new AppUser();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.valueOf(dto.getRole().toUpperCase()));
        userRepository.save(user);
    }
}