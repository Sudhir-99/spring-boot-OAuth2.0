package com.aouth.repository;

import com.aouth.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
 
import java.util.Optional;
 
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
 
    Optional<AppUser> findByUsername(String username);
 
    boolean existsByUsername(String username);
 
    boolean existsByEmail(String email);
}