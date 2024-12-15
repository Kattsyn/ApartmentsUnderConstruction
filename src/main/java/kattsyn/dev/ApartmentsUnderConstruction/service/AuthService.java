package kattsyn.dev.ApartmentsUnderConstruction.service;

import kattsyn.dev.ApartmentsUnderConstruction.dtos.RegistrationDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserService userService;


    public String getRegisterPage(Model model) {
        model.addAttribute("registrationDTO", new RegistrationDTO());

        return "auth/register-page";
    }

    public void register(RegistrationDTO registrationDTO) {
        userService.createUser(registrationDTO);
    }
}
