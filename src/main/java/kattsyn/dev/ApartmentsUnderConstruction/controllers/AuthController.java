package kattsyn.dev.ApartmentsUnderConstruction.controllers;

import kattsyn.dev.ApartmentsUnderConstruction.dtos.RegistrationDTO;
import kattsyn.dev.ApartmentsUnderConstruction.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@Slf4j
@RequestMapping("")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {

        if (error != null) {
            model.addAttribute("errorMessage", "Неправильный логин или пароль");
        }
        if (logout != null) {
            model.addAttribute("successMessage", "Вы успешно вышли из аккаунта");
        }

        return "auth/login-page";
    }

    @GetMapping("/secured")
    public String getSecuredPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();  // Получение имени пользователя
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();  // Роли пользователя
            // Выводим имя и роли в лог
            log.info("Authenticated user: " + username);
            authorities.forEach(authority -> log.info("Role: " + authority.getAuthority()));
        }
        return "authTest/test-page";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registrationDTO", new RegistrationDTO());
        return "auth/register-page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("registrationDTO") RegistrationDTO registrationDTO, BindingResult bindingResult) {
        registrationDTO.setStatus(true);
        authService.register(registrationDTO);
        return "redirect:/apartments";
    }

}
