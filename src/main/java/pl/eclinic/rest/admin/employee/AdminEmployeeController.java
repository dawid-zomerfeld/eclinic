package pl.eclinic.rest.admin.employee;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.eclinic.domain.Doctor;
import pl.eclinic.domain.Recepcionist;
import pl.eclinic.repository.DoctorJpaRepository;
import pl.eclinic.repository.RecepcionistJpaRepository;

import java.util.Set;

@CrossOrigin
@RestController
@PreAuthorize("hasAuthority('admin')")
public class AdminEmployeeController {

    private RecepcionistJpaRepository recepcionistJpaRepository;
    private DoctorJpaRepository doctorJpaRepository;

    public AdminEmployeeController(RecepcionistJpaRepository recepcionistJpaRepository, DoctorJpaRepository doctorJpaRepository) {
        this.recepcionistJpaRepository = recepcionistJpaRepository;
        this.doctorJpaRepository = doctorJpaRepository;
    }

    @GetMapping("/admin/employee/recepcionists")
    public Set<Recepcionist> getRecepcionists() {
        return recepcionistJpaRepository.findAll();
    }

    @GetMapping("/admin/employee/doctors")
    public Set<Doctor> getDoctors() {
        return doctorJpaRepository.findAll();
    }
}
