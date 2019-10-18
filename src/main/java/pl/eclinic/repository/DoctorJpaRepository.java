package pl.eclinic.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.eclinic.domain.Doctor;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

public interface DoctorJpaRepository extends CrudRepository<Doctor, Long> {

    Set<Doctor> findAll();
    Optional<Doctor> findByEmail(String email);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE Doctor d SET d.banned = :banned WHERE d.id = :id")
    void updateDoctorBanned(@Param("id") Long id, @Param("banned") Boolean banned);
}
