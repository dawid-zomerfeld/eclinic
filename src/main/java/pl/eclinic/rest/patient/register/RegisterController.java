package pl.eclinic.rest.patient.register;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.eclinic.repository.DoctorJpaRepository;
import pl.eclinic.repository.ManagerJpaRepository;
import pl.eclinic.repository.PatientJpaRepository;
import pl.eclinic.repository.RecepcionistJpaRepository;

import java.util.Optional;

@CrossOrigin
@RestController
public class RegisterController {

    private PatientJpaRepository patientJpaRepository;
    private DoctorJpaRepository doctorJpaRepository;
    private RecepcionistJpaRepository recepcionistJpaRepository;
    private ManagerJpaRepository managerJpaRepository;

    public RegisterController(PatientJpaRepository patientJpaRepository, DoctorJpaRepository doctorJpaRepository, RecepcionistJpaRepository recepcionistJpaRepository, ManagerJpaRepository managerJpaRepository) {
        this.patientJpaRepository = patientJpaRepository;
        this.doctorJpaRepository = doctorJpaRepository;
        this.recepcionistJpaRepository = recepcionistJpaRepository;
        this.managerJpaRepository = managerJpaRepository;
    }

    @PostMapping("/users/register")
    public ResponseEntity<HttpStatus> registerUser(@RequestBody final RegisterData registerData) {
        return new ResponseEntity<>(
                Optional.ofNullable(registerData)
                        .map(RegisterDataConverter::add)
                        .filter(value -> !patientJpaRepository.findByEmail(value.getEmail()).isPresent())
                        .filter(value -> !doctorJpaRepository.findByEmail(value.getEmail()).isPresent())
                        .filter(value -> !recepcionistJpaRepository.findByEmail(value.getEmail()).isPresent())
                        .filter(value -> !managerJpaRepository.findByEmail(value.getEmail()).isPresent())
                        .flatMap(value -> Optional.ofNullable(patientJpaRepository.save(value)))
                        .isPresent() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST
        );
    }
}
