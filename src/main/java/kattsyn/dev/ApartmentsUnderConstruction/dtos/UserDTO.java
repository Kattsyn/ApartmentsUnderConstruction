package kattsyn.dev.ApartmentsUnderConstruction.dtos;

import kattsyn.dev.ApartmentsUnderConstruction.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long userId;
    private String username;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private Boolean status;
    List<Role> roles;

}
