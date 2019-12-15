package pl.eclinic.rest.doctor.visits;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.eclinic.domain.Visit;
import pl.eclinic.services.DoctorService;

import java.util.Set;

@CrossOrigin
@RestController
@PreAuthorize("hasAuthority('doctor')")
public class DoctorVisitControler {

    private DoctorService doctorService;


    public DoctorVisitControler(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping(value = "/doctor/{id}/visits/{day}/{month}/{year}")
    public Set<Visit> getAllVisitsByDate(@PathVariable("id") Long id, @PathVariable("year") Integer year, @PathVariable("month") Integer month, @PathVariable("day") Integer day) {
        return doctorService.getAllVisitsByDate(id, day, month, year);
    }

    @GetMapping(value = "/doctor/{id}/visits")
    public Set<Visit> getAllVisits(@PathVariable("id") Long id) {
        return doctorService.getAllVisits(id);
    }

    @PatchMapping("/doctor/visits/details/{id}")
    public ResponseEntity updateVisitDetails(@PathVariable("id") Long id, @RequestBody final VisitsData visitsData) {
        return doctorService.updateVisitDetails(id, visitsData);
    }

    @PatchMapping(value = "/doctor/visits/done/{idVisit}")
    public ResponseEntity doneVisit(@PathVariable("idVisit") Long idVisit) {
        return doctorService.doneVisit(idVisit);
    }

    @GetMapping(value = "/doctor/{idDoctor}/patients/{idPatient}/visits")
    public Set<Visit> getVisitsByDoctorAndPatient(@PathVariable("idDoctor") Long idDoctor, @PathVariable("idPatient") Long idPatient ){
        return doctorService.getVisitsByDoctorAndPatient(idDoctor, idPatient);
    }
}
