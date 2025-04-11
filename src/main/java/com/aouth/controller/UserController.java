package com.aouth.controller;

import com.aouth.entity.AppUser;
import com.aouth.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private AppUserRepository userRepository;


    @GetMapping("/greet")
    public String getGreeting() {
        return "Hello, Sudhir!";
    }

    /*@GetMapping("/api/user")
    public Map<String, Object> userInfo(OAuth2AuthenticationToken authentication) {
        return authentication.getPrincipal().getAttributes();
    }*/

    @GetMapping("/api/user")
    public Map<String, Object> userInfo(Authentication authentication) {
        Map<String, Object> userDetails = new HashMap<>();

        if (authentication instanceof OAuth2AuthenticationToken oauthToken) {
            Map<String, Object> attributes = oauthToken.getPrincipal().getAttributes();

            userDetails.put("name", attributes.getOrDefault("name", "OAuth2 User"));
            userDetails.put("email", attributes.getOrDefault("email", "N/A"));
            userDetails.put("picture", attributes.getOrDefault("picture", ""));
        } else if (authentication instanceof UsernamePasswordAuthenticationToken) {
            String username = authentication.getName();
            AppUser user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            userDetails.put("name", user.getUsername()); // add fullName field to AppUser
            userDetails.put("email", user.getEmail());
            userDetails.put("picture", ""); // optional
        }

        return userDetails;
    }
}
