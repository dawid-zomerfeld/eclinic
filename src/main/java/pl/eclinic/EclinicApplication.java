package pl.eclinic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.eclinic.domain.Patient;
import pl.eclinic.repository.PatientJpaRepository;
import pl.eclinic.repository.PatientRepository;

import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class EclinicApplication {



	public static void main(String[] args) {
		SpringApplication.run(EclinicApplication.class, args);
	}

}
