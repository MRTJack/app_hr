package uz.pdp.app_hr_2.backend.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TaskDTO {
    private String title;
    private String description;
    private Date dueDate;
    private Long employeeId;
}
