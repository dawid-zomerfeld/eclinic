package pl.eclinic.repository;

import org.springframework.data.repository.CrudRepository;
import pl.eclinic.domain.Visit;

import java.util.Optional;
import java.util.Set;

public interface VisitJpaRepository extends CrudRepository<Visit, Long> {
    Set<Visit> findAllByDoctorIdAndDayAndMonthAndYear(Long Id, Integer day, Integer month, Integer year);
    Optional<Visit> findVisitByIdAndDoctorId(Long visitId, Long userId);
}

