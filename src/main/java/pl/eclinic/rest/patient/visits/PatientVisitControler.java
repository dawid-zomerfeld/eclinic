package pl.eclinic.rest.patient.visits;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.eclinic.domain.Doctor;
import pl.eclinic.domain.Visit;
import pl.eclinic.services.PatientService;

import java.util.Optional;
import java.util.Set;

@CrossOrigin
@RestController
@PreAuthorize("hasAuthority('patient')")
public class PatientVisitControler {

    private PatientService patientService;


    public PatientVisitControler(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patient/search/doctors")
    public Set<Doctor> getDoctors() {
        return patientService.findAllDoctors();
    }

    @GetMapping("/patient/search/doctors/{id}")
    public Optional<Doctor> getDoctor(@PathVariable("id") Long id) {
        return patientService.findDoctor(id);
    }

    @GetMapping(value = "/patient/search/doctors/{id}/visits/{day}/{month}/{year}")
    public Set<Visit> getDoctorVisits(@PathVariable("id") Long id, @PathVariable("year") Integer year, @PathVariable("month") Integer month, @PathVariable("day") Integer day) {
        return patientService.getVisits(id, day, month, year);
    }
}
