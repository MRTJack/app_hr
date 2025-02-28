package uz.pdp.app_hr_2.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_hr_2.backend.dto.LoginDTO;
import uz.pdp.app_hr_2.backend.dto.RegisterDTO;
import uz.pdp.app_hr_2.backend.payload.ApiResponse;
import uz.pdp.app_hr_2.backend.service.AuthService;

@Controller
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public HttpEntity<?> registerUser(@RequestBody RegisterDTO registerDTO) {
        ApiResponse apiResponse = authService.registerUser(registerDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDTO loginDTO) {
        ApiResponse apiResponse = authService.login(loginDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 401).body(apiResponse);
    }

}
