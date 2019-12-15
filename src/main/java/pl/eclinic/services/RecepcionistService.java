package pl.eclinic.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.eclinic.domain.Doctor;
import pl.eclinic.domain.Recepcionist;
import pl.eclinic.domain.Visit;
import pl.eclinic.repository.DoctorJpaRepository;
import pl.eclinic.repository.RecepcionistJpaRepository;
import pl.eclinic.repository.VisitJpaRepository;
import pl.eclinic.rest.recepcionist.visits.AddVisitsData;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static pl.eclinic.domain.VisitStatus.NEW;
import static pl.eclinic.domain.VisitStatus.PAID;

@Service
public class RecepcionistService {

    private RecepcionistJpaRepository recepcionistJpaRepository;
    private DoctorJpaRepository doctorJpaRepository;
    private VisitJpaRepository visitJpaRepository;

    public RecepcionistService(RecepcionistJpaRepository recepcionistJpaRepository, DoctorJpaRepository doctorJpaRepository, VisitJpaRepository visitJpaRepository) {
        this.recepcionistJpaRepository = recepcionistJpaRepository;
        this.doctorJpaRepository = doctorJpaRepository;
        this.visitJpaRepository = visitJpaRepository;
    }

    public Optional<Recepcionist> findRecepcionist(String email) {
        return recepcionistJpaRepository.findByEmail(email);
    }

    public Recepcionist registerRecepcionist(Recepcionist recepcionist) {
        return recepcionistJpaRepository.save(recepcionist);
    }

    public Set<Doctor> findAllDoctors() {
        return doctorJpaRepository.findAll();
    }

    public Optional<Doctor> findDoctor(Long id) {return doctorJpaRepository.findById(id);}

    public Set<Visit> getVisitsByDoctor(Long id, Integer day, Integer month, Integer year) {
        return visitJpaRepository.findAllByDoctorIdAndDayAndMonthAndYear(id, day, month, year);
    }

    public Set<Visit> getAllVisitsByDate(Integer day, Integer month, Integer year) {
        return visitJpaRepository.findAllByDayAndMonthAndYearAndPatientIsNotNull(day, month, year);
    }


    public ResponseEntity deleteVisit(Long visitId, Long doctorId) {
             visitJpaRepository.findVisitByIdAndDoctorId(visitId, doctorId)
                .ifPresent(visit -> {
                    visitJpaRepository.delete(visit);
                }
                );
        return ResponseEntity.accepted().build();
    }

    public ResponseEntity addVisits(Long id, AddVisitsData addVisitsData)  {

        Doctor doctor = doctorJpaRepository.findDoctorById(id);

        List<Visit> visitsToAdd = new ArrayList<>();
        Set<Visit> visits = visitJpaRepository.findAllByDoctorIdAndDayAndMonthAndYear(id, addVisitsData.getDay(), addVisitsData.getMonth(), addVisitsData.getYear());

        Integer firstHour = addVisitsData.getFirstHour();
        Integer firstMinutes = addVisitsData.getFirstMinutes();
        Integer duration = addVisitsData.getDuration();

        for (int i = 0; i < addVisitsData.getQuantityVisit(); i++) {
            Integer finalStartHour = firstHour;
            Integer finalStartMinutes = firstMinutes;
            Set<Visit> visitsInTime = visits.stream()
                    .filter(visit -> visit.getHour().equals(finalStartHour))
                    .filter(visit -> visit.getMinutes().equals(finalStartMinutes))
                    .collect(Collectors.toSet());

            if (visitsInTime.isEmpty()) {
                visitsToAdd.add(Visit.builder()
                        .status(NEW)
                        .doctor(doctor)
                        .price(addVisitsData.getPrice())
                        .duration(duration)
                        .year(addVisitsData.getYear())
                        .month(addVisitsData.getMonth())
                        .day(addVisitsData.getDay())
                        .hour(finalStartHour)
                        .minutes(finalStartMinutes)
                        .build());
                firstMinutes += duration;
                if (firstMinutes > 59) {
                    firstHour++;
                    firstMinutes -= 60;
                    if (firstHour > 23) {
                        break;
                    }
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        visitJpaRepository.saveAll(visitsToAdd);
        return ResponseEntity.accepted().build();
    }

    public ResponseEntity paidVisit(Long visitId) {
        visitJpaRepository.findVisitById(visitId)
                .map(visit -> {
                    visit.setStatus(PAID);
                    return  visitJpaRepository.save(visit);
                });
        return ResponseEntity.accepted().build();
    }
}
