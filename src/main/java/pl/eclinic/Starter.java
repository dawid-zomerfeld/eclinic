package pl.eclinic;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.eclinic.domain.*;
import pl.eclinic.repository.*;

import java.util.Optional;
import java.util.Set;


@Component
public class Starter implements CommandLineRunner {



    private PatientJpaRepository patientJpaRepository;
    private DoctorJpaRepository doctorJpaRepository;
    private VisitJpaRepository visitJpaRepository;
    private RecepcionistJpaRepository recepcionistJpaRepository;
    private ManagerJpaRepository managerJpaRepository;

    public Starter(PatientJpaRepository patientJpaRepository, DoctorJpaRepository doctorJpaRepository, VisitJpaRepository visitJpaRepository, RecepcionistJpaRepository recepcionistJpaRepository, ManagerJpaRepository managerJpaRepository) {
        this.patientJpaRepository = patientJpaRepository;
        this.doctorJpaRepository = doctorJpaRepository;
        this.visitJpaRepository = visitJpaRepository;
        this.recepcionistJpaRepository = recepcionistJpaRepository;
        this.managerJpaRepository = managerJpaRepository;
    }

    @Override
    public void run(String... args) throws Exception {

     //  Patient pacjent = new Patient("Adam", "Zomerfeld", "152123226", "Daleko 29", "26-080", "MNW", "75647", "aasdny@wp.pl" ,"123");
     //   patientJpaRepository.save(osoba);
       // patientJpaRepository.deleteById(5L);


     // Doctor doktorek = new Doctor("Igor", "Nowak", "Ginekolog", "doctor@doctor", "doctor");
      // doctorJpaRepository.save(doktorek);

      //   Recepcionist recepcionist = new Recepcionist("Adam", "Kowalski", "recepcionist@recepcionist", "recepcionist");
      //   recepcionistJpaRepository.save(recepcionist);


       //    Manager manager = new Manager("manager@manager", "manager");
        //   managerJpaRepository.save(manager);


      //  doctorJpaRepository.deleteById(1L);



       // Doctor doktorek = doctorJpaRepository.findOneById(2L);
      //  Patient pacjent = patientJpaRepository.findOneById(2L);

      //  Visit visit = new Visit(doktorek, pacjent);
      //  visitJpaRepository.save(visit);
      //  visitJpaRepository.deleteById(3L);


      //  System.out.println("Pacjenci");
      //  Set<Patient> patients = patientJpaRepository.findAll();



     //   for (Patient patient : patients) {
      //     System.out.println(patient);
      //  }


     //  System.out.println("Doktorzy");
     //   Set<Doctor> doctors = doctorJpaRepository.findAll();

     //   for (Doctor doctor : doctors) {
      //      System.out.println(doctor);
      //  }

     //   System.out.println("Wizyty");
      //  Set<Visit> visits = visitJpaRepository.findAll();

     //   for (Visit visi : visits) {
     //       System.out.println(visi);
     //   }

    }
}