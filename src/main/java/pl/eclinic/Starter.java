package pl.eclinic;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.eclinic.domain.Doctor;
import pl.eclinic.domain.Patient;
import pl.eclinic.repository.DoctorJpaRepository;
import pl.eclinic.repository.PatientJpaRepository;

import java.util.Optional;
import java.util.Set;


@Component
public class Starter implements CommandLineRunner {



    private PatientJpaRepository patientJpaRepository;
    private DoctorJpaRepository doctorJpaRepository;

    public Starter(PatientJpaRepository patientJpaRepository, DoctorJpaRepository doctorJpaRepository) {
        this.patientJpaRepository = patientJpaRepository;
        this.doctorJpaRepository = doctorJpaRepository;
    }

    @Override
    public void run(String... args) throws Exception {

     //   Patient osoba = new Patient("Adam", "Zomerfeld", "152226", "Daleko 29", "26-080", "MNW", "75647", "aayny@wp.pl" ,"123");
      //  patientJpaRepository.save(osoba);
       // patientJpaRepository.deleteById(5L);


  //      Doctor lekarz = new Doctor("Igor", "Nowak", "Ginekolog", "docto2r2@wp.pl", "tajne");
     //   doctorJpaRepository.save(lekarz);
     //   doctorJpaRepository.deleteById(7L);

        System.out.println("Pacjenci");
        Set<Patient> patients = patientJpaRepository.findAll();

        for (Patient patient : patients) {
           System.out.println(patient);
        }


        System.out.println("Doktorzy");
        Set<Doctor> doctors = doctorJpaRepository.findAll();

        for (Doctor doctor : doctors) {
            System.out.println(doctor);
        }

    }
}