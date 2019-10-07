package pl.eclinic.repository;

import org.springframework.data.repository.CrudRepository;
import pl.eclinic.domain.Recepcionist;

import java.util.Optional;
import java.util.Set;

public interface RecepcionistJpaRepository extends CrudRepository<Recepcionist, Long> {

    Recepcionist save(Recepcionist recepcionist);
    Optional<Recepcionist> findByEmail(String email);
    Set<Recepcionist> findAll();

}
