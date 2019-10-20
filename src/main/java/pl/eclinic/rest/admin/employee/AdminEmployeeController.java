package pl.eclinic.rest.admin.employee;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.eclinic.domain.Doctor;
import pl.eclinic.domain.Recepcionist;
import pl.eclinic.rest.admin.register.doctor.DoctorData;
import pl.eclinic.rest.admin.register.recepcionist.RecepcionistData;
import pl.eclinic.services.AdminService;
import java.util.Set;

@CrossOrigin
@RestController
@PreAuthorize("hasAuthority('admin')")
public class AdminEmployeeController {

    private AdminService adminservice;

    public AdminEmployeeController(AdminService adminservice) {
        this.adminservice = adminservice;
    }

    @GetMapping("/admin/employee/recepcionists")
    public Set<Recepcionist> getRecepcionists() {
        return adminservice.findAllRecepcionists();
    }

    @GetMapping("/admin/employee/doctors")
    public Set<Doctor> getDoctors() {
        return adminservice.findAllDoctors();
    }

    @PatchMapping("/admin/employee/recepcionist/{id}")
    public ResponseEntity updateRecepcionist(@PathVariable("id") Long id, @RequestBody final RecepcionistData recepcionistData) {
        return adminservice.changeRecepcionist(id, recepcionistData);
    }

    @PatchMapping("/admin/employee/doctor/{id}")
    public ResponseEntity updateDoctor(@PathVariable("id") Long id, @RequestBody final DoctorData doctorData) {
        return adminservice.changeDoctor(id, doctorData);
    }



}
