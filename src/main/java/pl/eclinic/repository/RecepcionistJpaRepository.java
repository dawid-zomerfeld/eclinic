package pl.eclinic.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.eclinic.domain.Recepcionist;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

public interface RecepcionistJpaRepository extends CrudRepository<Recepcionist, Long> {

    Optional<Recepcionist> findByEmail(String email);
    Set<Recepcionist> findAll();

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE Recepcionist r SET r.banned = :banned WHERE r.id = :id")
    void updateRecepcionistBanned(@Param("id") Long id, @Param("banned") Boolean banned);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE Recepcionist r SET r.firstName = :firstName, r.lastName = :lastName WHERE r.id = :id")
    void updateRecepcionist(@Param("id") Long id, @Param("firstName") String firstName, @Param("lastName") String lastName);

}
