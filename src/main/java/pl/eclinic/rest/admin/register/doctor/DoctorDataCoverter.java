package pl.eclinic.rest.admin.register.doctor;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.eclinic.domain.Doctor;

public class DoctorDataCoverter {

    private static PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    static public Doctor addDoctor(DoctorData doctorData) {
        return Doctor.builder()
                .email(doctorData.getEmail())
                .password(encoder.encode(doctorData.getPassword()))
                .firstName(doctorData.getFirstName())
                .lastName(doctorData.getLastName())
                .banned(false)
                .specialization(doctorData.getSpecialization())
                .build();
    }
}
