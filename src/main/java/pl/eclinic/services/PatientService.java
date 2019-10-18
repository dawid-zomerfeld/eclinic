package pl.eclinic.services;

import org.springframework.stereotype.Service;
import pl.eclinic.domain.Patient;
import pl.eclinic.repository.PatientJpaRepository;

import java.util.Optional;

@Service
public class PatientService {

    private PatientJpaRepository patientJpaRepository;

    public PatientService(PatientJpaRepository patientJpaRepository) {
        this.patientJpaRepository = patientJpaRepository;
    }

    public Optional<Patient> findPatient(String email) {
        return patientJpaRepository.findByEmail(email);
    }

    public Patient registerPatient(Patient patient) {
        return patientJpaRepository.save(patient);
    }
}
