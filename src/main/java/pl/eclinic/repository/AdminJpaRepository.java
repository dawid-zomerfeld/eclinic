package pl.eclinic.repository;

import org.springframework.data.repository.CrudRepository;
import pl.eclinic.domain.Admin;

import java.util.Optional;

public interface AdminJpaRepository extends CrudRepository<Admin, Long> {
    Admin save(Admin admin);
    Optional<Admin> findByEmail(String email);
}
