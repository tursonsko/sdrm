package pjatk.sdrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pjatk.sdrm.model.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
