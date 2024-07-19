package uz.pdp.app_hr_2.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.app_hr_2.backend.models.Salary;

import java.util.List;

public interface SalaryRepository extends JpaRepository<Salary, Long> {
        List<Salary> findByEmployeeId(Long employeeId);

        @Query("SELECT s FROM Salary s WHERE MONTH(s.date) = :month AND YEAR(s.date) = :year")
        List<Salary> findByMonthAndYear(@Param("month") int month, @Param("year") int year);
    }
