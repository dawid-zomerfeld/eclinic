package pl.eclinic.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.eclinic.domain.Visit;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

public interface VisitJpaRepository extends CrudRepository<Visit, Long> {
    Set<Visit> findAllByDoctorIdAndDayAndMonthAndYear(Long Id, Integer day, Integer month, Integer year);
    Optional<Visit> findVisitByIdAndDoctorId(Long visitId, Long userId);
    Optional<Visit> findVisitByIdAndPatientIsNull(Long visitId);
    Set<Visit> findAllByDayAndMonthAndYearAndPatientIsNotNull(Integer day, Integer month, Integer year);
    Optional<Visit> findVisitById(Long visitId);
    Set<Visit> findAllByPatientId(Long Id);
    Set<Visit> findAllByDoctorIdAndDayAndMonthAndYearAndPatientIsNotNull(Long Id, Integer day, Integer month, Integer year);
    Set<Visit> findAllByDoctorIdAndPatientIsNotNull(Long Id);
    Set<Visit> findAllByDoctorIdAndPatientId(Long doctorId, Long patientId);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE Visit v SET v.prescription = :prescription, v.diagnosis = :diagnosis, v.recommendations = :recommendations WHERE v.id = :id")
    void updateVisitDetails(@Param("id") Long id, @Param("prescription") String prescription, @Param("diagnosis") String diagnosis, @Param("recommendations") String recommendations);

}

