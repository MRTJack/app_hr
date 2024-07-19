package uz.pdp.app_hr_2.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_hr_2.backend.service.AuthService;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    AuthService authService;

    @GetMapping("/login?error=true")
    public String loginError(Model model) {
        model.addAttribute("error", "Invalid email or password.");
        return "login";
    }

    @GetMapping("/login?logout")
    public String logoutPage(Model model) {
        model.addAttribute("message", "You have been logged out successfully.");
        return "login";
    }
}

