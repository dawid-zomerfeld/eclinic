package pl.eclinic.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "visit")
public class Visit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Doctor.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name="id_doctor")
    private Doctor doctor;

    @ManyToOne(targetEntity = Patient.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name="id_patient")
    private Patient patient;

    public Visit(Doctor doctor, Patient patient) {
        this.doctor = doctor;
        this.patient = patient;
    }
}
