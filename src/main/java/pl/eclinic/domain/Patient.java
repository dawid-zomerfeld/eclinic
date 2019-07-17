package pl.eclinic.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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

    @Column(name="pesel")
    private String pesel = "";

    @Column(name="adress")
    private String adress = "";

    @Size(min = 6, max = 6)
    @Pattern(regexp = "[0-9]{2}\\-[0-9]{3}")
    @Column(name="postcode")
    private String postcode = "00-000";

    @Column(name="town")
    private String town = "";

    @Column(name="telephone")
    private String telephone = "";

    @Column(name="email")
     private String email = "";

   @Column(name="password")
    private String password = "";





}