package kattsyn.dev.ApartmentsUnderConstruction.service;

import kattsyn.dev.ApartmentsUnderConstruction.dtos.RegistrationDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserService userService;

    @Transactional
    public void register(RegistrationDTO registrationDTO) {
        userService.createUser(registrationDTO);
    }
}
