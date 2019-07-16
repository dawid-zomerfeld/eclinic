package pl.eclinic.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_patient")
    private Long idPatient;
    @Column(name="first_name")
    private String firstName = "";
    @Column(name="last_name")
    private String lastName = "";


    public Patient(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}