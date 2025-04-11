package com.aouth.entity;

import jakarta.persistence.*;
import lombok.Data;
 
@Entity
@Data
@Table(name = "tbl_app_user")
public class AppUser {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
 
    @Enumerated(EnumType.STRING)
    private Role role;
}