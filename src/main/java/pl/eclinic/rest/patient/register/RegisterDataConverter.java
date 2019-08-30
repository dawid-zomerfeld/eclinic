package pl.eclinic.rest.patient.register;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.eclinic.domain.Patient;

public class RegisterDataConverter {

    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    static Patient add(RegisterData registerData) {
        return Patient.builder()
                .email(registerData.getEmail())
                .password(bCryptPasswordEncoder.encode(registerData.getPassword()))
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
