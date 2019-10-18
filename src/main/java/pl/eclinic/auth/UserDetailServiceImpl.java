package pl.eclinic.auth;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import pl.eclinic.domain.Admin;
import pl.eclinic.domain.Doctor;
import pl.eclinic.domain.Patient;
import pl.eclinic.domain.Recepcionist;
import pl.eclinic.repository.AdminJpaRepository;
import pl.eclinic.repository.DoctorJpaRepository;
import pl.eclinic.repository.PatientJpaRepository;
import pl.eclinic.repository.RecepcionistJpaRepository;

import java.util.Arrays;
import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private PatientJpaRepository patientJpaRepository;
    private DoctorJpaRepository doctorJpaRepository;
    private RecepcionistJpaRepository recepcionistJpaRepository;
    private AdminJpaRepository adminJpaRepository;

    public UserDetailServiceImpl(PatientJpaRepository patientJpaRepository, DoctorJpaRepository doctorJpaRepository, RecepcionistJpaRepository recepcionistJpaRepository, AdminJpaRepository adminJpaRepository) {
        this.patientJpaRepository = patientJpaRepository;
        this.doctorJpaRepository = doctorJpaRepository;
        this.recepcionistJpaRepository = recepcionistJpaRepository;
        this.adminJpaRepository = adminJpaRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Patient> patientByEmail = patientJpaRepository.findByEmail(email);
        Optional<Doctor>  doctorByEmail = doctorJpaRepository.findByEmail(email);
        Optional<Recepcionist> rececpcionistByEmail = recepcionistJpaRepository.findByEmail(email);
        Optional<Admin> adminByEmail = adminJpaRepository.findByEmail(email);
        if (patientByEmail.isPresent()) {
            Patient patient = patientByEmail.get();
            return new org.springframework.security.core.userdetails.
                    User(patient.getEmail(), patient.getPassword(), Arrays.asList(new SimpleGrantedAuthority("patient"), new SimpleGrantedAuthority(String.valueOf(patient.getId()))));
        }

        else if (doctorByEmail.isPresent()) {
            Doctor doctor = doctorByEmail.get();
            if (!doctor.getBanned()) {
                return new org.springframework.security.core.userdetails.
                        User(doctor.getEmail(), doctor.getPassword(), Arrays.asList(new SimpleGrantedAuthority("doctor"), new SimpleGrantedAuthority(String.valueOf(doctor.getId()))));
            }
            else {
                throw new UsernameNotFoundException("User with email " + email +" is banned");
            }
        }
        else if (rececpcionistByEmail.isPresent()) {
            Recepcionist recepcionist = rececpcionistByEmail.get();
            if (!recepcionist.getBanned()) {
                return new org.springframework.security.core.userdetails.
                        User(recepcionist.getEmail(), recepcionist.getPassword(), Arrays.asList(new SimpleGrantedAuthority("recepcionist"), new SimpleGrantedAuthority(String.valueOf(recepcionist.getId()))));
            }
            else {
                throw new UsernameNotFoundException("User with email " + email +" is banned");
            }
        }

        else if (adminByEmail.isPresent()) {
            Admin admin = adminByEmail.get();
            return new org.springframework.security.core.userdetails.
                    User(admin.getEmail(), admin.getPassword(), Arrays.asList(new SimpleGrantedAuthority("admin"), new SimpleGrantedAuthority(String.valueOf(admin.getId()))));
        }

        else {
            throw new UsernameNotFoundException("User with email " + email +" doesn't exist");
        }
    }
}
