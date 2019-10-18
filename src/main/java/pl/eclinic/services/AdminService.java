package pl.eclinic.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.eclinic.domain.Admin;
import pl.eclinic.domain.Doctor;
import pl.eclinic.domain.Recepcionist;
import pl.eclinic.repository.AdminJpaRepository;
import pl.eclinic.repository.DoctorJpaRepository;
import pl.eclinic.repository.RecepcionistJpaRepository;
import pl.eclinic.rest.admin.register.doctor.DoctorData;
import pl.eclinic.rest.admin.register.recepcionist.RecepcionistData;
import java.util.Optional;
import java.util.Set;

@Service
public class AdminService {

    private RecepcionistJpaRepository recepcionistJpaRepository;
    private DoctorJpaRepository doctorJpaRepository;
    private AdminJpaRepository adminJpaRepository;

    public AdminService(RecepcionistJpaRepository recepcionistJpaRepository, DoctorJpaRepository doctorJpaRepository, AdminJpaRepository adminJpaRepository) {
        this.recepcionistJpaRepository = recepcionistJpaRepository;
        this.doctorJpaRepository = doctorJpaRepository;
        this.adminJpaRepository = adminJpaRepository;
    }

    public Optional<Admin> findAdmin(String email) {
        return adminJpaRepository.findByEmail(email);
    }

    public ResponseEntity changeRecepcionistBanned(Long id, RecepcionistData recepcionistData) {
        Boolean banned = recepcionistData.getBanned();
        recepcionistJpaRepository.updateRecepcionistBanned(id, banned);
        return ResponseEntity.accepted().build();
    }

    public ResponseEntity changeDoctorBanned(Long id, DoctorData doctorData) {
        Boolean banned = doctorData.getBanned();
        doctorJpaRepository.updateDoctorBanned(id, banned);
        return ResponseEntity.accepted().build();
    }

    public Set<Recepcionist> findAllRecepcionists() {
        return recepcionistJpaRepository.findAll();
    }

    public Set<Doctor> findAllDoctors() {
        return doctorJpaRepository.findAll();
    }
}
