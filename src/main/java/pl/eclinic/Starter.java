package pl.eclinic;

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
    }

    @Override
    public void run(String... args) throws Exception {

       // Patient osoba = new Patient("Iga", "Zomerfeld", "12323423123", "Daleko 29", "267080", "MNW", "75647", "aasddny@wp.pl" ,"123");
      //  patientJpaRepository.save(osoba);

       // patientJpaRepository.deleteById(5L);

        Set<Patient> patients = patientJpaRepository.findAll();

        for (Patient patient : patients) {
            System.out.println(patient);
        }


    }
}