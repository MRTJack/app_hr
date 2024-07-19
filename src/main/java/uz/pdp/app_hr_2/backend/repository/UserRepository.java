package uz.pdp.app_hr_2.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.app_hr_2.backend.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    Optional<User> findByEmailAndEmailCode(String email, String emailCode);

    Optional<User> findByEmail(String email);
}