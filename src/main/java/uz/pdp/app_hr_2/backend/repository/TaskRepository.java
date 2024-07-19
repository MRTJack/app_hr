package uz.pdp.app_hr_2.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.app_hr_2.backend.models.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByAssignedEmployeeId(Long employeeId);
}