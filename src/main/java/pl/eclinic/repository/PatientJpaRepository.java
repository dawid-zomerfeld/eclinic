package pl.eclinic.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.eclinic.domain.Patient;

import javax.transaction.Transactional;
import java.util.Optional;

public interface PatientJpaRepository extends CrudRepository<Patient, Long> {

    Optional<Patient> findByEmail(String email);
    Patient findPatientById(Long id);
    Optional<Patient> findById(Long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE Patient p SET p.firstName = :firstName, p.lastName = :lastName, p.pesel = :pesel, p.address = :address, p.postcode = :postcode, p.town = :town, p.telephone = :telephone WHERE p.id = :id")
    void updatePatient(@Param("id") Long id, @Param("firstName") String firstName, @Param("lastName") String lastName,
                       @Param("pesel") String pesel, @Param("address") String address, @Param("postcode") String postcode,
                       @Param("town") String town, @Param("telephone") String telephone);
}