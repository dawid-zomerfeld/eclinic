package pl.eclinic.repository;

import org.springframework.data.repository.CrudRepository;
import pl.eclinic.domain.Doctor;

import java.util.Optional;
import java.util.Set;

public interface DoctorJpaRepository extends CrudRepository<Doctor, Long> {


    Set<Doctor> findAll();
    Optional<Doctor> findById(Long id);
    Doctor save(Doctor doctor);
}
