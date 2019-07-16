package pl.eclinic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.eclinic.domain.Patient;
import pl.eclinic.repository.PatientJpaRepository;

import java.util.Optional;
import java.util.Set;


@Component
public class Starter implements CommandLineRunner {



    private PatientJpaRepository patientJpaRepository;


    public Starter(PatientJpaRepository patientJpaRepository) {
        this.patientJpaRepository = patientJpaRepository;
        System.out.println("Teraz ja");
    }

    @Override
    public void run(String... args) throws Exception {


        Set<Patient> patients = patientJpaRepository.findAll();

        for (Patient patient : patients) {
            System.out.println(patient);
        }

        Optional<Patient> typ = patientJpaRepository.findByFirstName("Iga");
        System.out.println(typ);
    }
}
