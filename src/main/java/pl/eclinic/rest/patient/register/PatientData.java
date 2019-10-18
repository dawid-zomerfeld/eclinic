package pl.eclinic.rest.patient.register;


import lombok.*;

import org.hibernate.validator.constraints.Email;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
@ToString
public class PatientData {

    @Email
    private String email;

    @Size(min = 6, max = 20, message = "This field is required and must contain 6-20 characters")
    private String password;

    @Size(min = 2, max = 20, message = "This field is required and must contain 2-20 characters")
    private String firstName;

    @Size(min = 2, max = 20, message = "This field is required and must contain 2-20 characters")
    private String lastName;

    @Size(min = 11, max = 11, message = "This field is required and must contain 11 characters")
    private String pesel;

    @Size(min = 2, max = 20, message = "This field is required and must contain 2-20 characters")
    private String address;

    @Size(min = 6, max = 6, message = "This field is required and must contain 6 characters")
    private String postcode;

    @Size(min = 2, max = 20, message = "This field is required and must contain 2-20 characters")
    private String town;

    @Size(min = 8, max = 12, message = "This field is required and must contain 8-12 characters")
    private String telephone;
}
