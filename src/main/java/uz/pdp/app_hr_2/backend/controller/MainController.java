package uz.pdp.app_hr_2.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/api/main")
public class MainController {

    @GetMapping("/")
    public String mainPage() {
        return "main";
    }
}
