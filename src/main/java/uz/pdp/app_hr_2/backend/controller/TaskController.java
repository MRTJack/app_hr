package uz.pdp.app_hr_2.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_hr_2.backend.dto.TaskDTO;
import uz.pdp.app_hr_2.backend.models.Task;
import uz.pdp.app_hr_2.backend.models.enums.TaskStatus;
import uz.pdp.app_hr_2.backend.payload.ApiResponse;
import uz.pdp.app_hr_2.backend.service.TaskService;

import java.util.List;

@Controller
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createTask(@RequestBody TaskDTO taskDTO) {
        ApiResponse apiResponse = taskService.createTask(taskDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @PutMapping("/update/{taskId}")
    public ResponseEntity<ApiResponse> updateTaskStatus(@PathVariable Long taskId, @RequestParam TaskStatus status) {
        ApiResponse apiResponse = taskService.updateTaskStatus(taskId, status);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Task>> getTasksByEmployeeId(@PathVariable Long employeeId) {
        List<Task> tasks = taskService.getTasksByEmployeeId(employeeId);
        return ResponseEntity.ok(tasks);
    }
}
