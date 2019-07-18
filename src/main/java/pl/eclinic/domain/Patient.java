package pl.eclinic.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="first_name")
    private String firstName = "";

    @Column(name="last_name")
    private String lastName = "";

    @Column(unique = true)
    private String pesel = "";

    private String address = "";

    @Size(min = 6, max = 6)
    @Pattern(regexp = "[0-9]{2}\\-[0-9]{3}")
    private String postcode = "00-000";

    private String town = "";

    private String telephone = "";

    @Column(unique = true)
    private String email = "";

    private String password = "";

    public Patient(String firstName, String lastName, String pesel, String address, @Size(min = 6, max = 6) @Pattern(regexp = "[0-9]{2}\\-[0-9]{3}") String postcode, String town, String telephone, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.address = address;
        this.postcode = postcode;
        this.town = town;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
    }
}