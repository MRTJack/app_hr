package uz.pdp.app_hr_2.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_hr_2.backend.models.Salary;
import uz.pdp.app_hr_2.backend.payload.ApiResponse;
import uz.pdp.app_hr_2.backend.service.SalaryService;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/api/salaries")
public class SalaryController {
    @Autowired
    private SalaryService salaryService;

    @PostMapping("/record")
    public ResponseEntity<ApiResponse> recordSalary(@RequestParam Long employeeId, @RequestParam BigDecimal amount) {
        ApiResponse apiResponse = salaryService.recordSalary(employeeId, amount);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Salary>> getSalariesByEmployeeId(@PathVariable Long employeeId) {
        List<Salary> salaries = salaryService.getSalariesByEmployeeId(employeeId);
        return ResponseEntity.ok(salaries);
    }

    @GetMapping("/month")
    public ResponseEntity<List<Salary>> getSalariesByMonth(@RequestParam int month, @RequestParam int year) {
        List<Salary> salaries = salaryService.getSalariesByMonth(month, year);
        return ResponseEntity.ok(salaries);
    }
}