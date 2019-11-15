package pl.eclinic.services;

import org.springframework.stereotype.Service;
import pl.eclinic.domain.Doctor;
import pl.eclinic.domain.Patient;
import pl.eclinic.domain.Visit;
import pl.eclinic.repository.DoctorJpaRepository;
import pl.eclinic.repository.PatientJpaRepository;
import pl.eclinic.repository.VisitJpaRepository;

import java.util.Optional;
import java.util.Set;

@Service
public class PatientService {

    private PatientJpaRepository patientJpaRepository;
    private DoctorJpaRepository doctorJpaRepository;
    private VisitJpaRepository visitJpaRepository;

    public PatientService(PatientJpaRepository patientJpaRepository, DoctorJpaRepository doctorJpaRepository, VisitJpaRepository visitJpaRepository) {
        this.patientJpaRepository = patientJpaRepository;
        this.doctorJpaRepository = doctorJpaRepository;
        this.visitJpaRepository = visitJpaRepository;
    }

    public Set<Doctor> findAllDoctors() {
        return doctorJpaRepository.findAll();
    }

    public Optional<Doctor> findDoctor(Long id) {return doctorJpaRepository.findById(id);}

    public Optional<Patient> findPatient(String email) {
        return patientJpaRepository.findByEmail(email);
    }

    public Patient registerPatient(Patient patient) {
        return patientJpaRepository.save(patient);
    }

    public Set<Visit> getVisits(Long id, Integer day, Integer month, Integer year) {
        return visitJpaRepository.findAllByDoctorIdAndDayAndMonthAndYear(id, day, month, year);
    }
}
