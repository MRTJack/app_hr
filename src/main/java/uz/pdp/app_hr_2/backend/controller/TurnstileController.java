package uz.pdp.app_hr_2.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_hr_2.backend.models.Turnstile;
import uz.pdp.app_hr_2.backend.models.enums.TurnstileAction;
import uz.pdp.app_hr_2.backend.payload.ApiResponse;
import uz.pdp.app_hr_2.backend.service.TurnstileService;

import java.util.List;

@Controller
@RequestMapping("/api/turnstile")
public class TurnstileController {
    @Autowired
    private TurnstileService turnstileService;

    @PostMapping("/record")
    public ResponseEntity<ApiResponse> recordTurnstileAction(@RequestParam Long employeeId, @RequestParam TurnstileAction action) {
        ApiResponse apiResponse = turnstileService.recordTurnstileAction(employeeId, action);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Turnstile>> getTurnstileActionsByEmployeeId(@PathVariable Long employeeId) {
        List<Turnstile> actions = turnstileService.getTurnstileActionsByEmployeeId(employeeId);
        return ResponseEntity.ok(actions);
    }
}
