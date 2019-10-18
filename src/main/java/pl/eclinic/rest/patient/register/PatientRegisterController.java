package pl.eclinic.rest.patient.register;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.eclinic.services.AdminService;
import pl.eclinic.services.DoctorService;
import pl.eclinic.services.PatientService;
import pl.eclinic.services.RecepcionistService;
import java.util.Optional;

@CrossOrigin
@RestController
public class PatientRegisterController {

    private PatientService patientService;
    private DoctorService doctorService;
    private RecepcionistService recepcionistService;
    private AdminService adminService;

    public PatientRegisterController(PatientService patientService, DoctorService doctorService, RecepcionistService recepcionistService, AdminService adminService) {
        this.adminService = adminService;
        this.doctorService = doctorService;
        this.recepcionistService = recepcionistService;
        this.patientService = patientService;
    }

    @PostMapping("/users/register")
    public ResponseEntity<String> registerUser(@RequestBody final PatientData patientData) {
        return new ResponseEntity<>(
                Optional.ofNullable(patientData)
                        .map(PatientDataConverter::add)
                        .filter(value -> !patientService.findPatient(value.getEmail()).isPresent())
                        .filter(value -> !doctorService.findDoctor(value.getEmail()).isPresent())
                        .filter(value -> !recepcionistService.findRecepcionist(value.getEmail()).isPresent())
                        .filter(value -> !adminService.findAdmin(value.getEmail()).isPresent())
                        .flatMap(value -> Optional.ofNullable(patientService.registerPatient(value)))
                        .isPresent() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST
        );
    }
}
