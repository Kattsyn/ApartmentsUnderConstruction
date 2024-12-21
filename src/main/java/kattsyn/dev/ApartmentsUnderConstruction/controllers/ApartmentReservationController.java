package kattsyn.dev.ApartmentsUnderConstruction.controllers;

import kattsyn.dev.ApartmentsUnderConstruction.service.ApartmentReservationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@RequestMapping("/reservations")
public class ApartmentReservationController {

    private final ApartmentReservationService reservationService;
    private final ApartmentReservationService apartmentReservationService;

    @GetMapping("/")
    public String getReservations(Model model) {
        model.addAttribute("reservations", reservationService.findAll());

        return "reservations/index";
    }

    @GetMapping("/confirm")
    public String confirmReservation(@RequestParam("reservationId") Long reservationId) {
        apartmentReservationService.confirmReservation(reservationId);

        return "redirect:/reservations/";
    }

    @GetMapping("/delete")
    public String deleteReservation(@RequestParam("reservationId") Long reservationId) {
        apartmentReservationService.deleteReservation(reservationId);
        return "redirect:/reservations/";
    }


}
