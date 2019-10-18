package pl.eclinic.services;

import org.springframework.stereotype.Service;
import pl.eclinic.domain.Doctor;
import pl.eclinic.domain.Recepcionist;
import pl.eclinic.repository.DoctorJpaRepository;
import pl.eclinic.repository.RecepcionistJpaRepository;
import java.util.Optional;
import java.util.Set;

@Service
public class RecepcionistService {

    private RecepcionistJpaRepository recepcionistJpaRepository;
    private DoctorJpaRepository doctorJpaRepository;

    public RecepcionistService(RecepcionistJpaRepository recepcionistJpaRepository, DoctorJpaRepository doctorJpaRepository) {
        this.recepcionistJpaRepository = recepcionistJpaRepository;
        this.doctorJpaRepository = doctorJpaRepository;
    }

    public Optional<Recepcionist> findRecepcionist(String email) {
        return recepcionistJpaRepository.findByEmail(email);
    }

    public Recepcionist registerRecepcionist(Recepcionist recepcionist) {
        return recepcionistJpaRepository.save(recepcionist);
    }

    public Set<Doctor> findAllDoctors() {
        return doctorJpaRepository.findAll();
    }
}
