package pl.eclinic.rest.admin.register.recepcionist;


import lombok.*;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
@ToString
public class RecepcionistData {

    @Email
    private String email;

    @Size(min = 6, max = 20, message = "This field is required and must contain 6-20 characters")
    private String password;

    @Size(min = 2, max = 20, message = "This field is required and must contain 2-20 characters")
    private String firstName;

    @Size(min = 2, max = 20, message = "This field is required and must contain 2-20 characters")
    private String lastName;

    private Boolean banned;
}
