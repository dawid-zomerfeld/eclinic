package pl.eclinic.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name")
    private String firstName = "";

    @Column(name="last_name")
    private String lastName = "";

    private String specialization = "";

    @Column(unique = true)
    private String email = "";

    private String password = "";

    @OneToMany(mappedBy = "doctor")
    private Set<Visit> visits = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(id, doctor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Doctor(String firstName, String lastName, String specialization, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", specialization='" + specialization + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}




