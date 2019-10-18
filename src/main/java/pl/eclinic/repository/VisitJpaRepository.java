package pl.eclinic.repository;

import org.springframework.data.repository.CrudRepository;
import pl.eclinic.domain.Visit;

public interface VisitJpaRepository extends CrudRepository<Visit, Long> {
}
