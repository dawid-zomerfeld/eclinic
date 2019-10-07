package pl.eclinic.rest.admin.register.recepcionist;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.eclinic.domain.Recepcionist;

public class RecepcionistDataConverter {

    private static PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    static public Recepcionist addRecepcionist(RecepcionistData recepcionistData) {
        return Recepcionist.builder()
                .email(recepcionistData.getEmail())
                .password(encoder.encode(recepcionistData.getPassword()))
                .firstName(recepcionistData.getFirstName())
                .lastName(recepcionistData.getLastName())
                .banned(false)
                .build();
    }
}
