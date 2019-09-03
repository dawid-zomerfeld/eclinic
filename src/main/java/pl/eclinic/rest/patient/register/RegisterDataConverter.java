package pl.eclinic.rest.patient.register;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.eclinic.domain.Patient;

public class RegisterDataConverter {

    private static PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    static Patient add(RegisterData registerData) {
        return Patient.builder()
                .email(registerData.getEmail())
                .password(encoder.encode(registerData.getPassword()))
                .firstName(registerData.getFirstName())
                .lastName(registerData.getLastName())
                .pesel(registerData.getPesel())
                .address(registerData.getAddress())
                .postcode(registerData.getPostcode())
                .town(registerData.getTown())
                .telephone(registerData.getTelephone())
                .build();
    }
}
