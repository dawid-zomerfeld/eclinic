package pl.eclinic.rest.patient.register;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.eclinic.domain.Patient;

public class PatientDataConverter {

    private static PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    static Patient add(PatientData patientData) {
        return Patient.builder()
                .email(patientData.getEmail())
                .password(encoder.encode(patientData.getPassword()))
                .firstName(patientData.getFirstName())
                .lastName(patientData.getLastName())
                .pesel(patientData.getPesel())
                .address(patientData.getAddress())
                .postcode(patientData.getPostcode())
                .town(patientData.getTown())
                .telephone(patientData.getTelephone())
                .build();
    }
}
