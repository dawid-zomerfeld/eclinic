package pl.eclinic.rest.patient.visits;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.eclinic.domain.Doctor;
import pl.eclinic.domain.Patient;
import pl.eclinic.domain.Visit;
import pl.eclinic.rest.patient.register.PatientData;
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

    @PostMapping(value = "/patient/{idPatient}/reserve/visits/{idVisit}")
    public ResponseEntity reserveVisit(@PathVariable("idPatient") Long idPatient, @PathVariable("idVisit") Long idVisit) {
        return patientService.reserveVisit(idVisit, idPatient);
    }
    @GetMapping("/patient/account/{id}")
    public Optional<Patient> getPatient(@PathVariable("id") Long id) {
        return patientService.findPatient(id);
    }

    @PatchMapping("/patient/account/update/{id}")
    public ResponseEntity updatePatient(@PathVariable("id") Long id, @RequestBody final PatientData patientData) {
        return patientService.changePatient(id, patientData);
    }

    @GetMapping(value = "/patient/visits/{id}")
    public Set<Visit> getPatientVisits(@PathVariable("id") Long id ){
        return patientService.getVisitsByPatient(id);
    }

    @PostMapping(value = "/patient/visits/cancel/{idVisit}")
    public ResponseEntity cancelVisit(@PathVariable("idVisit") Long idVisit) {
        return patientService.cancelVisit(idVisit);
    }
}
