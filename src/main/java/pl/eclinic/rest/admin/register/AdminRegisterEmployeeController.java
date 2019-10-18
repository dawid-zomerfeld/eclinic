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
import pl.eclinic.services.AdminService;
import pl.eclinic.services.DoctorService;
import pl.eclinic.services.PatientService;
import pl.eclinic.services.RecepcionistService;

import java.util.Optional;

@CrossOrigin
@RestController
@PreAuthorize("hasAuthority('admin')")
public class AdminRegisterEmployeeController {

    private PatientService patientService;
    private DoctorService doctorService;
    private RecepcionistService recepcionistService;
    private AdminService adminService;


    public AdminRegisterEmployeeController(PatientService patientService, DoctorService doctorService, RecepcionistService recepcionistService, AdminService adminService) {
        this.adminService = adminService;
        this.doctorService = doctorService;
        this.recepcionistService = recepcionistService;
        this.patientService = patientService;
    }

    @PostMapping("/admin/add/recepcionist")
    public ResponseEntity<HttpStatus> registerRecepcionist(@RequestBody final RecepcionistData recepcionistData) {
        return new ResponseEntity<>(
                Optional.ofNullable(recepcionistData)
                        .map(RecepcionistDataConverter::addRecepcionist)
                        .filter(value -> !patientService.findPatient(value.getEmail()).isPresent())
                        .filter(value -> !doctorService.findDoctor(value.getEmail()).isPresent())
                        .filter(value -> !recepcionistService.findRecepcionist(value.getEmail()).isPresent())
                        .filter(value -> !adminService.findAdmin(value.getEmail()).isPresent())
                        .flatMap(value -> Optional.ofNullable(recepcionistService.registerRecepcionist(value)))
                        .isPresent() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST
        );
    }

    @PostMapping("/admin/add/doctor")
    public ResponseEntity<HttpStatus> registerRecepcionist(@RequestBody final DoctorData doctorData) {
        return new ResponseEntity<>(
                Optional.ofNullable(doctorData)
                        .map(DoctorDataCoverter::addDoctor)
                        .filter(value -> !patientService.findPatient(value.getEmail()).isPresent())
                        .filter(value -> !doctorService.findDoctor(value.getEmail()).isPresent())
                        .filter(value -> !recepcionistService.findRecepcionist(value.getEmail()).isPresent())
                        .filter(value -> !adminService.findAdmin(value.getEmail()).isPresent())
                        .flatMap(value -> Optional.ofNullable(doctorService.registerDoctor(value)))
                        .isPresent() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST
        );
    }
}
