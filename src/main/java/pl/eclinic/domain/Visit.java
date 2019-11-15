package pl.eclinic.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Visit")
public class Visit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Doctor.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name="id_doctor")
    private Doctor doctor;

    @JsonIgnore
    @ManyToOne(targetEntity = Patient.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name="id_patient")
    private Patient patient;

    private Integer price;

    private String status = VisitStatus.NEW;

    private Integer duration;

    private Integer year;

    private Integer month;

    private Integer day;

    private Integer hour;

    private Integer minutes;

}
