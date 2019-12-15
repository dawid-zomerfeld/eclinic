package pl.eclinic.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.eclinic.domain.Doctor;
import pl.eclinic.domain.Visit;
import pl.eclinic.repository.DoctorJpaRepository;
import pl.eclinic.repository.VisitJpaRepository;
import pl.eclinic.rest.doctor.visits.VisitsData;

import java.util.Optional;
import java.util.Set;

import static pl.eclinic.domain.VisitStatus.DONE;

@Service
public class DoctorService {

    private DoctorJpaRepository doctorJpaRepository;
    private VisitJpaRepository visitJpaRepository;

    public DoctorService(DoctorJpaRepository doctorJpaRepository, VisitJpaRepository visitJpaRepository) {
        this.doctorJpaRepository = doctorJpaRepository;
        this.visitJpaRepository = visitJpaRepository;
    }

    public Optional<Doctor> findDoctor(String email) {
        return doctorJpaRepository.findByEmail(email);
    }

    public Doctor registerDoctor(Doctor doctor) {
        return doctorJpaRepository.save(doctor);
    }

    public Set<Visit> getAllVisitsByDate(Long id, Integer day, Integer month, Integer year) {
        return visitJpaRepository.findAllByDoctorIdAndDayAndMonthAndYearAndPatientIsNotNull(id, day, month, year);
    }

    public Set<Visit> getAllVisits(Long id) {
        return visitJpaRepository.findAllByDoctorIdAndPatientIsNotNull(id);
    }

    public ResponseEntity updateVisitDetails(Long id, VisitsData visitsData) {
        String prescription = visitsData.getPrescription();
        String diagnosis = visitsData.getDiagnosis();
        String recommendations = visitsData.getRecommendations();
        visitJpaRepository.updateVisitDetails(id, prescription, diagnosis, recommendations);
        return ResponseEntity.accepted().build();
    }

    public ResponseEntity doneVisit(Long visitId) {
        visitJpaRepository.findVisitById(visitId)
                .map(visit -> {
                    visit.setStatus(DONE);
                    return  visitJpaRepository.save(visit);
                });
        return ResponseEntity.accepted().build();
    }

    public Set<Visit> getVisitsByDoctorAndPatient(Long idDoctor, Long idPatient) {
        return visitJpaRepository.findAllByDoctorIdAndPatientId(idDoctor, idPatient);
    }
}
