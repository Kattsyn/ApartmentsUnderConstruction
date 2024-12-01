package kattsyn.dev.ApartmentsUnderConstruction.service;

import kattsyn.dev.ApartmentsUnderConstruction.dtos.RegistrationDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserService userService;


    public String getRegisterPage(Model model) {
        model.addAttribute("registrationDTO", new RegistrationDTO());

        return "auth/register-page";
    }

    public String register(RegistrationDTO registrationDTO, BindingResult bindingResult) {

        userService.createUser(registrationDTO);

        return "redirect:/apartments";
    }
}
