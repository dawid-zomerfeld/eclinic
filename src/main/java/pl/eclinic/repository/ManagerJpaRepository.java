package pl.eclinic.repository;

import org.springframework.data.repository.CrudRepository;
import pl.eclinic.domain.Manager;

import java.util.Optional;

public interface ManagerJpaRepository extends CrudRepository<Manager, Long> {
    Manager save(Manager manager);
    Optional<Manager> findByEmail(String email);
}
