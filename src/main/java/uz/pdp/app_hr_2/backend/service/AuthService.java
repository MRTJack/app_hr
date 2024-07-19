package uz.pdp.app_hr_2.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.app_hr_2.backend.dto.LoginDTO;
import uz.pdp.app_hr_2.backend.dto.RegisterDTO;
import uz.pdp.app_hr_2.backend.models.User;
import uz.pdp.app_hr_2.backend.models.enums.RoleName;
import uz.pdp.app_hr_2.backend.payload.ApiResponse;
import uz.pdp.app_hr_2.backend.repository.RoleRepository;
import uz.pdp.app_hr_2.backend.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    @Lazy
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    EmailService emailService;
    @Autowired
    private JwtService jwtService;

    public ApiResponse registerUser(RegisterDTO registerDTO) {
        boolean b = userRepository.existsByEmail(registerDTO.getEmail());
        if (b) {
            return new ApiResponse("This email has been registered for a long time", false);
        }
        User user = new User();
        user.setFirst_name(registerDTO.getFirst_name());
        user.setLast_name(registerDTO.getLast_name());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setRoles(Collections.singleton(roleRepository.findByRoleName(RoleName.DIRECTOR)));
        user.setEmailCode(UUID.randomUUID().toString());
        userRepository.save(user);

        emailService.sendEmail(user.getEmail(), user.getEmailCode());
        return new ApiResponse("Account is success registered", true);
    }


    public ApiResponse login(LoginDTO loginDTO) {
        Optional<User> userOptional = userRepository.findByEmail(loginDTO.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
                String token = jwtService.generateToken(user, user.getRoles());
                return new ApiResponse("Login successful", true, token);
            }
        }
        return new ApiResponse("Invalid email or  password", false);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }
}
