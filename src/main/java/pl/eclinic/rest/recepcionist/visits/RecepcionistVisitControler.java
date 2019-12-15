package pl.eclinic.rest.recepcionist.visits;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.eclinic.domain.Doctor;
import pl.eclinic.domain.Visit;
import pl.eclinic.services.RecepcionistService;

import java.util.Optional;
import java.util.Set;

@CrossOrigin
@RestController
@PreAuthorize("hasAuthority('recepcionist')")
public class RecepcionistVisitControler {

    private RecepcionistService recepcionistService;


    public RecepcionistVisitControler(RecepcionistService recepcionistService) {
        this.recepcionistService = recepcionistService;
    }

    @GetMapping("/recepcionist/add/doctors")
    public Set<Doctor> getDoctors() {
        return recepcionistService.findAllDoctors();
    }

    @GetMapping("/recepcionist/add/doctors/{id}")
    public Optional<Doctor> getDoctor(@PathVariable("id") Long id) {
        return recepcionistService.findDoctor(id);
    }

    @PostMapping(value = "/recepcionist/add/doctors/{id}/visits")
    public ResponseEntity addVisits(@PathVariable("id") Long id, @RequestBody AddVisitsData addVisitsData) {
        return recepcionistService.addVisits(id, addVisitsData);
    }

    @DeleteMapping(value = "/recepcionist/add/doctors/{idDoctor}/visits/{idVisit}")
    public ResponseEntity deleteVisit(@PathVariable("idVisit") Long idVisit, @PathVariable("idDoctor") Long idDoctor) {
        return recepcionistService.deleteVisit(idVisit, idDoctor);
    }

    @GetMapping(value = "/recepcionist/add/doctors/{id}/visits/{day}/{month}/{year}")
    public Set<Visit> getDoctorVisits(@PathVariable("id") Long id, @PathVariable("year") Integer year, @PathVariable("month") Integer month, @PathVariable("day") Integer day) {
        return recepcionistService.getVisitsByDoctor(id, day, month, year);
    }

    @GetMapping(value = "/recepcionist/search/visits/{day}/{month}/{year}")
    public Set<Visit> getAllVisitsByDate(@PathVariable("year") Integer year, @PathVariable("month") Integer month, @PathVariable("day") Integer day) {
        return recepcionistService.getAllVisitsByDate(day, month, year);
    }

    @PostMapping(value = "/recepcionist/search/visits/{idVisit}")
    public ResponseEntity paidVisit(@PathVariable("idVisit") Long idVisit) {
        return recepcionistService.paidVisit(idVisit);
    }
}
