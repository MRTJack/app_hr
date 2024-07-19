package uz.pdp.app_hr_2.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.app_hr_2.backend.models.Turnstile;

import java.util.List;

public interface TurnstileRepository extends JpaRepository<Turnstile,Long> {

    List<Turnstile> findByEmployeeId(Long employeeId);
}
