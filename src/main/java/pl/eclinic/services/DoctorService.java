package pl.eclinic.services;

import org.springframework.stereotype.Service;
import pl.eclinic.domain.Doctor;
import pl.eclinic.repository.DoctorJpaRepository;

import java.util.Optional;

@Service
public class DoctorService {

    private DoctorJpaRepository doctorJpaRepository;

    public DoctorService(DoctorJpaRepository doctorJpaRepository) {
        this.doctorJpaRepository = doctorJpaRepository;
    }

    public Optional<Doctor> findDoctor(String email) {
        return doctorJpaRepository.findByEmail(email);
    }

    public Doctor registerDoctor(Doctor doctor) {
        return doctorJpaRepository.save(doctor);
    }
}
