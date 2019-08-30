package pl.eclinic.rest.patient.register;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.eclinic.repository.PatientJpaRepository;

import java.util.Optional;

@CrossOrigin
@RestController
public class RegisterController {

    private PatientJpaRepository patientJpaRepository;

    public RegisterController(PatientJpaRepository patientJpaRepository) {
        this.patientJpaRepository = patientJpaRepository;
    }

    @PostMapping("/users/register")
    public ResponseEntity<HttpStatus> registerUser(@RequestBody final RegisterData registerData) {
        return new ResponseEntity<>(
                Optional.ofNullable(registerData)
                        .map(RegisterDataConverter::add)
                        .filter(value -> !patientJpaRepository.findByEmail(value.getEmail()).isPresent())
                        .flatMap(value -> Optional.ofNullable(patientJpaRepository.save(value)))
                        .isPresent() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST
        );
    }
}
