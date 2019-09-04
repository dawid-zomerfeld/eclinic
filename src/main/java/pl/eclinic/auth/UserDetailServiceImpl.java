package pl.eclinic.auth;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import pl.eclinic.domain.Doctor;
import pl.eclinic.domain.Manager;
import pl.eclinic.domain.Patient;
import pl.eclinic.domain.Recepcionist;
import pl.eclinic.repository.DoctorJpaRepository;
import pl.eclinic.repository.ManagerJpaRepository;
import pl.eclinic.repository.PatientJpaRepository;
import pl.eclinic.repository.RecepcionistJpaRepository;

import java.util.Arrays;
import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private PatientJpaRepository patientJpaRepository;
    private DoctorJpaRepository doctorJpaRepository;
    private RecepcionistJpaRepository recepcionistJpaRepository;
    private ManagerJpaRepository managerJpaRepository;

    public UserDetailServiceImpl(PatientJpaRepository patientJpaRepository, DoctorJpaRepository doctorJpaRepository, RecepcionistJpaRepository recepcionistJpaRepository, ManagerJpaRepository managerJpaRepository) {
        this.patientJpaRepository = patientJpaRepository;
        this.doctorJpaRepository = doctorJpaRepository;
        this.recepcionistJpaRepository = recepcionistJpaRepository;
        this.managerJpaRepository = managerJpaRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Patient> patientByEmail = patientJpaRepository.findByEmail(email);
        Optional<Doctor>  doctorByEmail = doctorJpaRepository.findByEmail(email);
        Optional<Recepcionist> rececpcionistByEmail = recepcionistJpaRepository.findByEmail(email);
        Optional<Manager> managerByEmail = managerJpaRepository.findByEmail(email);
        if (patientByEmail.isPresent()) {
            Patient patient = patientByEmail.get();
            return new org.springframework.security.core.userdetails.
                    User(patient.getEmail(), patient.getPassword(), Arrays.asList(new SimpleGrantedAuthority("patient"), new SimpleGrantedAuthority(String.valueOf(patient.getId()))));
        }

        else if (doctorByEmail.isPresent()) {
            Doctor doctor = doctorByEmail.get();
            return new org.springframework.security.core.userdetails.
                    User(doctor.getEmail(), doctor.getPassword(), Arrays.asList(new SimpleGrantedAuthority("doctor"), new SimpleGrantedAuthority(String.valueOf(doctor.getId()))));
        }

        else if (rececpcionistByEmail.isPresent()) {
            Recepcionist recepcionist = rececpcionistByEmail.get();
            return new org.springframework.security.core.userdetails.
                    User(recepcionist.getEmail(), recepcionist.getPassword(), Arrays.asList(new SimpleGrantedAuthority("recepcionist"), new SimpleGrantedAuthority(String.valueOf(recepcionist.getId()))));
        }

        else if (managerByEmail.isPresent()) {
            Manager manager = managerByEmail.get();
            return new org.springframework.security.core.userdetails.
                    User(manager.getEmail(), manager.getPassword(), Arrays.asList(new SimpleGrantedAuthority("manager"), new SimpleGrantedAuthority(String.valueOf(manager.getId()))));
        }

        else {
            throw new UsernameNotFoundException("User with email " + email +" doesn't exist");
        }
    }
}
