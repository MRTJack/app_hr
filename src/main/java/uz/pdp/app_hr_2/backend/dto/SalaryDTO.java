package uz.pdp.app_hr_2.backend.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SalaryDTO {
    private Long employeeId;
    private BigDecimal amount;
}
