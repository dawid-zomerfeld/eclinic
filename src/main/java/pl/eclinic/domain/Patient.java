package pl.eclinic.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    @Column(name="pesel", unique = true)
    private String pesel = "";
    @Column(name="adress")
    private String adress = "";
    @Column(name="postcode")
    private String postcode = "00-000";
    @Column(name="twon")
    private String town = "";
    @Column(name="telephone")
    private String telephone = "";
    @Column(name="email", unique = true)
    private String email = "";
    @Column(name="password")
    private String password = "";




    public Patient(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}