package pl.eclinic.repository;

import org.springframework.data.repository.CrudRepository;
import pl.eclinic.domain.Doctor;

import java.util.Set;

public interface DoctorJpaRepository extends CrudRepository<Doctor, Long> {


    Set<Doctor> findAll();
    Doctor save(Doctor doctor);
}
