package uz.pdp.app_hr_2.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.app_hr_2.backend.dto.TaskDTO;
import uz.pdp.app_hr_2.backend.models.Task;
import uz.pdp.app_hr_2.backend.models.User;
import uz.pdp.app_hr_2.backend.models.enums.TaskStatus;
import uz.pdp.app_hr_2.backend.repository.UserRepository;
import uz.pdp.app_hr_2.backend.payload.ApiResponse;
import uz.pdp.app_hr_2.backend.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public ApiResponse createTask(TaskDTO taskDTO) {
        Optional<User> optionalUser = userRepository.findById(taskDTO.getEmployeeId());
        if (optionalUser.isEmpty()) {
            return new ApiResponse("Employee not found", false);
        }
        User employee = optionalUser.get();
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setDueDate(taskDTO.getDueDate());
        task.setAssignedEmployee(employee);
        task.setStatus(TaskStatus.NEW);
        taskRepository.save(task);

        emailService.sendEmail(employee.getEmail(), "You have been assigned a new task: " + task.getTitle());
        return new ApiResponse("Task created successfully", true);
    }

    public ApiResponse updateTaskStatus(Long taskId, TaskStatus status) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isEmpty()) {
            return new ApiResponse("Task not found", false);
        }
        Task task = optionalTask.get();
        task.setStatus(status);
        taskRepository.save(task);

        emailService.sendEmail(task.getAssignedEmployee().getEmail(), "Task status updated: " + task.getTitle());
        return new ApiResponse("Task status updated successfully", true);
    }

    public List<Task> getTasksByEmployeeId(Long employeeId) {
        return taskRepository.findByAssignedEmployeeId(employeeId);
    }
}

