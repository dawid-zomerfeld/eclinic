package pl.eclinic.repository;

import org.springframework.data.repository.CrudRepository;
import pl.eclinic.domain.Patient;
import java.util.Optional;

public interface PatientJpaRepository extends CrudRepository<Patient, Long> {

    Optional<Patient> findByEmail(String email);
}