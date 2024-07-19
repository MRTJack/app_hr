package uz.pdp.app_hr_2.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.app_hr_2.backend.models.User;
import uz.pdp.app_hr_2.backend.repository.TurnstileRepository;
import uz.pdp.app_hr_2.backend.models.Turnstile;
import uz.pdp.app_hr_2.backend.models.enums.TurnstileAction;
import uz.pdp.app_hr_2.backend.payload.ApiResponse;
import uz.pdp.app_hr_2.backend.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TurnstileService {
    @Autowired
    private TurnstileRepository turnstileRepository;

    @Autowired
    private UserRepository userRepository;

    public ApiResponse recordTurnstileAction(Long employeeId, TurnstileAction action) {
        Optional<User> optionalUser = userRepository.findById(employeeId);
        if (optionalUser.isEmpty()) {
            return new ApiResponse("Employee not found", false);
        }
        User employee = optionalUser.get();
        Turnstile turnstile = new Turnstile();
        turnstile.setEmployee(employee);
        turnstile.setAction(action);
        turnstileRepository.save(turnstile);

        return new ApiResponse("Turnstile action recorded successfully", true);
    }

    public List<Turnstile> getTurnstileActionsByEmployeeId(Long employeeId) {
        return turnstileRepository.findByEmployeeId(employeeId);
    }
}
