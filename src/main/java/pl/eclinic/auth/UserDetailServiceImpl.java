package pl.eclinic.auth;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import pl.eclinic.domain.Patient;
import pl.eclinic.repository.PatientJpaRepository;

import java.util.Arrays;
import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private PatientJpaRepository patientJpaRepository;

    public UserDetailServiceImpl(PatientJpaRepository patientJpaRepository) {
        this.patientJpaRepository = patientJpaRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Patient> patientByEmail = patientJpaRepository.findByEmail(email);
        if (patientByEmail.isPresent()) {
            Patient patient = patientByEmail.get();
            return new org.springframework.security.core.userdetails.
                    User(patient.getEmail(), patient.getPassword(), Arrays.asList(new SimpleGrantedAuthority("patient"), new SimpleGrantedAuthority(String.valueOf(patient.getId()))));
        } else {
            throw new UsernameNotFoundException("User with email " + email +" doesn't exist");
        }
    }
}
