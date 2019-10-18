package pl.eclinic.rest.recepcionist.addVisit;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.eclinic.domain.Doctor;
import pl.eclinic.services.RecepcionistService;

import java.util.Set;

@CrossOrigin
@RestController
@PreAuthorize("hasAuthority('recepcionist')")
public class RecepcionistAddVisitControler {

    private RecepcionistService recepcionistService;

    public RecepcionistAddVisitControler(RecepcionistService recepcionistService) {
        this.recepcionistService = recepcionistService;
    }

    @GetMapping("/recepcionist/add/doctors")
    public Set<Doctor> getDoctors() {
        return recepcionistService.findAllDoctors();
    }
}
