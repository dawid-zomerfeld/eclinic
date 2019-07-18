package pl.eclinic.repository;

import org.springframework.data.repository.CrudRepository;
import pl.eclinic.domain.Patient;

import java.util.Optional;
import java.util.Set;

public interface PatientJpaRepository extends CrudRepository<Patient, Long> {

    Set<Patient> findAll();
    Patient save(Patient patient);




}