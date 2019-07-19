package pl.eclinic;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.eclinic.domain.Doctor;
import pl.eclinic.domain.Patient;
import pl.eclinic.domain.Visit;
import pl.eclinic.repository.DoctorJpaRepository;
import pl.eclinic.repository.PatientJpaRepository;
import pl.eclinic.repository.VisitJpaRepository;

import java.util.Optional;
import java.util.Set;


@Component
public class Starter implements CommandLineRunner {



    private PatientJpaRepository patientJpaRepository;
    private DoctorJpaRepository doctorJpaRepository;
    private VisitJpaRepository visitJpaRepository;

    public Starter(PatientJpaRepository patientJpaRepository, DoctorJpaRepository doctorJpaRepository, VisitJpaRepository visitJpaRepository) {
        this.patientJpaRepository = patientJpaRepository;
        this.doctorJpaRepository = doctorJpaRepository;
        this.visitJpaRepository = visitJpaRepository;
    }

    @Override
    public void run(String... args) throws Exception {

     //  Patient pacjent = new Patient("Adam", "Zomerfeld", "152123226", "Daleko 29", "26-080", "MNW", "75647", "aasdny@wp.pl" ,"123");
     //   patientJpaRepository.save(osoba);
       // patientJpaRepository.deleteById(5L);


     // Doctor doktorek = new Doctor("Igor", "Nowak", "Ginekolog", "docto2r2@wp.pl", "tajne");
     //  doctorJpaRepository.save(lekarz);
      //  doctorJpaRepository.deleteById(1L);



       // Doctor doktorek = doctorJpaRepository.findOneById(2L);
      //  Patient pacjent = patientJpaRepository.findOneById(2L);

      //  Visit visit = new Visit(doktorek, pacjent);
      //  visitJpaRepository.save(visit);
      //  visitJpaRepository.deleteById(3L);


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

        System.out.println("Wizyty");
        Set<Visit> visits = visitJpaRepository.findAll();

        for (Visit visi : visits) {
            System.out.println(visi);
        }

    }
}