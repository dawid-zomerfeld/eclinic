package pl.eclinic.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.eclinic.domain.Doctor;
import pl.eclinic.domain.Patient;
import pl.eclinic.domain.Visit;
import pl.eclinic.repository.DoctorJpaRepository;
import pl.eclinic.repository.PatientJpaRepository;
import pl.eclinic.repository.VisitJpaRepository;
import pl.eclinic.rest.patient.register.PatientData;

import javax.validation.constraints.Null;
import java.util.Optional;
import java.util.Set;

import static pl.eclinic.domain.VisitStatus.*;

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

    public Optional<Patient> findPatient(Long id) {return patientJpaRepository.findById(id);}

    public Optional<Patient> findPatient(String email) {
        return patientJpaRepository.findByEmail(email);
    }

    public Patient registerPatient(Patient patient) {
        return patientJpaRepository.save(patient);
    }

    public Set<Visit> getVisits(Long id, Integer day, Integer month, Integer year) {
        return visitJpaRepository.findAllByDoctorIdAndDayAndMonthAndYear(id, day, month, year);
    }

    public ResponseEntity changePatient(Long id, PatientData patientData) {
        String firstName = patientData.getFirstName();
        String lastName = patientData.getLastName();
        String pesel = patientData.getPesel();
        String address = patientData.getAddress();
        String postcode = patientData.getPostcode();
        String town = patientData.getTown();
        String telephone = patientData.getTelephone();
        patientJpaRepository.updatePatient(id, firstName, lastName, pesel, address, postcode, town, telephone);
        return ResponseEntity.accepted().build();
    }

    public Set<Visit> getVisitsByPatient(Long id) {
        return visitJpaRepository.findAllByPatientId(id);
    }

    public ResponseEntity reserveVisit(Long visitId, Long patientId) {
        visitJpaRepository.findVisitByIdAndPatientIsNull(visitId)
                .map(visit -> {
                    visit.setStatus(RESERVED);
                    visit.setPatient(patientJpaRepository.findPatientById(patientId));
                 return  visitJpaRepository.save(visit);
                });
        return ResponseEntity.accepted().build();
    }

    public ResponseEntity cancelVisit(Long visitId) {
        visitJpaRepository.findVisitById(visitId)
                .map(visit -> {
                    visit.setStatus(NEW);
                    visit.setPatient(null);
                    return  visitJpaRepository.save(visit);
                });
        return ResponseEntity.accepted().build();
    }

    public ResponseEntity payVisit(Long visitId) {
        visitJpaRepository.findVisitById(visitId)
                .map(visit -> {
                    visit.setStatus(PAID);
                    return  visitJpaRepository.save(visit);
                });
        return ResponseEntity.accepted().build();
    }
}
