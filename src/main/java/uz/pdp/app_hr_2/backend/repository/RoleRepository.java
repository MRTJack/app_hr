package uz.pdp.app_hr_2.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.app_hr_2.backend.models.Role;
import uz.pdp.app_hr_2.backend.models.enums.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(RoleName roleName);
}