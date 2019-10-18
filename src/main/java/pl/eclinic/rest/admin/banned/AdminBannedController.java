package pl.eclinic.rest.admin.banned;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.eclinic.rest.admin.register.doctor.DoctorData;
import pl.eclinic.rest.admin.register.recepcionist.RecepcionistData;
import pl.eclinic.services.AdminService;

@CrossOrigin
@RestController
@PreAuthorize("hasAuthority('admin')")
public class AdminBannedController {

    private AdminService adminservice;

    public AdminBannedController(AdminService adminservice) {
        this.adminservice = adminservice;
    }

    @PatchMapping("/admin/banned/recepcionist/{id}")
    public ResponseEntity updateRecepcionistBanned(@PathVariable("id") Long id, @RequestBody final RecepcionistData recepcionistData) {
        return adminservice.changeRecepcionistBanned(id, recepcionistData);
    }

    @PatchMapping("/admin/banned/doctor/{id}")
    public ResponseEntity updateDoctorBanned(@PathVariable("id") Long id, @RequestBody final DoctorData doctorData) {
        return adminservice.changeDoctorBanned(id, doctorData);
    }

}
