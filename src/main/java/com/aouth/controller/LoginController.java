package com.aouth.controller;

import com.aouth.dto.UserRegistrationDto;
import com.aouth.service.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController {

    @Autowired
    private AppUserDetailsService appUserDetailsService;

    @GetMapping("/login")
    public RedirectView loginRedirect() {
        return new RedirectView("/login.html");
    }

    @GetMapping("/home")
    public RedirectView homeRedirect() {
        return new RedirectView("/home.html");
    }

    @GetMapping("/register")
    public RedirectView registerRedirect() {
        return new RedirectView("/register.html");
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserRegistrationDto dto, RedirectAttributes redirectAttributes) {
        if (appUserDetailsService.existsByUsernameOrEmail(dto.getUsername(), dto.getEmail())) {
            redirectAttributes.addFlashAttribute("error", "Username or email already exists!");
            return "redirect:/register";
        }

        appUserDetailsService.registerNewUser(dto);
        return "redirect:/login";
    }

}
