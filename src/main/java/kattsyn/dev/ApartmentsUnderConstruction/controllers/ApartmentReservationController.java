package kattsyn.dev.ApartmentsUnderConstruction.controllers;

import kattsyn.dev.ApartmentsUnderConstruction.service.ApartmentReservationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/reservations")
public class ApartmentReservationController {

    private final ApartmentReservationService reservationService;

    @GetMapping("/")
    public String getReservations(Model model) {
        model.addAttribute("reservations", reservationService.findAll());

        return "reservations/index";
    }


}
