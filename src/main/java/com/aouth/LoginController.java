package com.aouth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController {

    @GetMapping("/login")
    public RedirectView loginRedirect() {
        return new RedirectView("/login.html"); // Redirect to the static file
    }

    @GetMapping("/home")
    public RedirectView homeRedirect() {
        return new RedirectView("/home.html"); // Redirect to the static file
    }
}
