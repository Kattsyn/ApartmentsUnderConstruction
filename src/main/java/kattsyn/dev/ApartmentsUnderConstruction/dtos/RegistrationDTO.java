package kattsyn.dev.ApartmentsUnderConstruction.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO {

    private Long userId;
    private String username;
    private String password;
    private String confirmPassword;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;

}
