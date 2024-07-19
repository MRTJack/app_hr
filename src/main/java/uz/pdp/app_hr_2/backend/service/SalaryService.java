package uz.pdp.app_hr_2.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.app_hr_2.backend.models.Salary;
import uz.pdp.app_hr_2.backend.models.User;
import uz.pdp.app_hr_2.backend.repository.SalaryRepository;
import uz.pdp.app_hr_2.backend.payload.ApiResponse;
import uz.pdp.app_hr_2.backend.repository.UserRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SalaryService {
    @Autowired
    private SalaryRepository salaryRepository;

    @Autowired
    private UserRepository userRepository;

    public ApiResponse recordSalary(Long employeeId, BigDecimal amount) {
        Optional<User> optionalUser = userRepository.findById(employeeId);
        if (optionalUser.isEmpty()) {
            return new ApiResponse("Employee not found", false);
        }
        User employee = optionalUser.get();
        Salary salary = new Salary();
        salary.setEmployee(employee);
        salary.setAmount(amount);
        salary.setDate(new Date());
        salaryRepository.save(salary);

        return new ApiResponse("Salary recorded successfully", true);
    }

    public List<Salary> getSalariesByEmployeeId(Long employeeId) {
        return salaryRepository.findByEmployeeId(employeeId);
    }

    public List<Salary> getSalariesByMonth(int month, int year) {
        return salaryRepository.findByMonthAndYear(month, year);
    }
}
