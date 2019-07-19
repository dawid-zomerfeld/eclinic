package pl.eclinic.repository;

import org.springframework.data.repository.CrudRepository;
import pl.eclinic.domain.Doctor;
import pl.eclinic.domain.Patient;
import pl.eclinic.domain.Visit;

import java.util.Set;

public interface VisitJpaRepository extends CrudRepository<Visit, Long> {

    Set<Visit> findAll();
    Visit save(Visit visit);
}
