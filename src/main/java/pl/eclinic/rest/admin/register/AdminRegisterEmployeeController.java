package pl.eclinic.rest.admin.register;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.eclinic.repository.AdminJpaRepository;
import pl.eclinic.repository.DoctorJpaRepository;
import pl.eclinic.repository.PatientJpaRepository;
import pl.eclinic.repository.RecepcionistJpaRepository;
import pl.eclinic.rest.admin.register.doctor.DoctorData;
import pl.eclinic.rest.admin.register.doctor.DoctorDataCoverter;
import pl.eclinic.rest.admin.register.recepcionist.RecepcionistData;
import pl.eclinic.rest.admin.register.recepcionist.RecepcionistDataConverter;

import java.util.Optional;

@CrossOrigin
@RestController
@PreAuthorize("hasAuthority('admin')")
public class AdminRegisterEmployeeController {

    private PatientJpaRepository patientJpaRepository;
    private DoctorJpaRepository doctorJpaRepository;
    private RecepcionistJpaRepository recepcionistJpaRepository;
    private AdminJpaRepository adminJpaRepository;

    public AdminRegisterEmployeeController(PatientJpaRepository patientJpaRepository, DoctorJpaRepository doctorJpaRepository, RecepcionistJpaRepository recepcionistJpaRepository, AdminJpaRepository adminJpaRepository) {
        this.patientJpaRepository = patientJpaRepository;
        this.doctorJpaRepository = doctorJpaRepository;
        this.recepcionistJpaRepository = recepcionistJpaRepository;
        this.adminJpaRepository = adminJpaRepository;
    }

    @PostMapping("/admin/add/recepcionist")
    public ResponseEntity<HttpStatus> registerRecepcionist(@RequestBody final RecepcionistData recepcionistData) {
        return new ResponseEntity<>(
                Optional.ofNullable(recepcionistData)
                        .map(RecepcionistDataConverter::addRecepcionist)
                        .filter(value -> !patientJpaRepository.findByEmail(value.getEmail()).isPresent())
                        .filter(value -> !doctorJpaRepository.findByEmail(value.getEmail()).isPresent())
                        .filter(value -> !recepcionistJpaRepository.findByEmail(value.getEmail()).isPresent())
                        .filter(value -> !adminJpaRepository.findByEmail(value.getEmail()).isPresent())
                        .flatMap(value -> Optional.ofNullable(recepcionistJpaRepository.save(value)))
                        .isPresent() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST
        );
    }

    @PostMapping("/admin/add/doctor")
    public ResponseEntity<HttpStatus> registerRecepcionist(@RequestBody final DoctorData doctorData) {
        return new ResponseEntity<>(
                Optional.ofNullable(doctorData)
                        .map(DoctorDataCoverter::addDoctor)
                        .filter(value -> !patientJpaRepository.findByEmail(value.getEmail()).isPresent())
                        .filter(value -> !doctorJpaRepository.findByEmail(value.getEmail()).isPresent())
                        .filter(value -> !recepcionistJpaRepository.findByEmail(value.getEmail()).isPresent())
                        .filter(value -> !adminJpaRepository.findByEmail(value.getEmail()).isPresent())
                        .flatMap(value -> Optional.ofNullable(doctorJpaRepository.save(value)))
                        .isPresent() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST
        );
    }
}
