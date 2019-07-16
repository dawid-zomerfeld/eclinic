package pl.eclinic.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.eclinic.domain.Patient;
import pl.eclinic.repository.PatientJpaRepository;

import java.util.Optional;
import java.util.Set;

@RestController
public class PatientRest {

    private PatientJpaRepository patientJpaRepository;

    public PatientRest(PatientJpaRepository patientJpaRepository) {
        this.patientJpaRepository = patientJpaRepository;
    }

    @RequestMapping("/patients")
    public Set<Patient> getPatients() {
        return patientJpaRepository.findAll();
    }
    @RequestMapping("/patients/{firstName}")
    public Optional<Patient> getPatient(@PathVariable("firstName") String firstName) {
        return patientJpaRepository.findByFirstName(firstName);
    }
}
