package pl.eclinic.rest.doctor.visits;

import lombok.Data;
import pl.eclinic.domain.Doctor;
import pl.eclinic.domain.Patient;

@Data
public class VisitsData {
    private Integer duration;
    private Integer year;
    private Integer month;
    private Integer day;
    private Integer firstHour;
    private Integer firstMinutes;
    private Integer quantityVisit;
    private Integer price;
    private Patient patient;
    private Doctor doctor;
    private String prescription;
    private String diagnosis;
    private String recommendations;
}
