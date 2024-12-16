package kattsyn.dev.ApartmentsUnderConstruction.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountDisabledException.class)
    public String handleAccountDisabledException(AccountDisabledException ex, Model model) {
        //model.addAttribute("errorMessage", ex.getMessage());
        return "redirect:/login?deactivated";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException ex, HttpServletRequest request, Model model) {

        String referer = request.getHeader("Referer");

        /*
        model.addAttribute("errorMessage", ex.getMessage());

        if (referer != null) {
            model.addAttribute("referer", referer);
        }
         */

        return "redirect:" + (referer != null ? referer : "/");
    }

}
