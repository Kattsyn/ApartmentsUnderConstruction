package kattsyn.dev.ApartmentsUnderConstruction.controllers;

import kattsyn.dev.ApartmentsUnderConstruction.dtos.ApartmentDTO;
import kattsyn.dev.ApartmentsUnderConstruction.service.ApartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/apartments")
@AllArgsConstructor
public class ApartmentController {

    private final ApartmentService apartmentService;

    @GetMapping("/info")
    public String showInfoPage(Model model, @RequestParam Long id) {
        return apartmentService.showInfoPage(model, id);
    }


    @GetMapping({"", "/"})
    public String showApartmentsList(Model model) {
        return apartmentService.showApartmentsList(model);
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        return apartmentService.showCreatePage(model);
    }

    @PostMapping("/create")
    public String createHouse(@Valid @ModelAttribute("apartmentDTO") ApartmentDTO apartmentDTO, BindingResult bindingResult) {
        return apartmentService.createApartment(apartmentDTO, bindingResult);
    }

    @GetMapping("/edit")
    public String showEditPage(Model model, @RequestParam Long id) {
        return apartmentService.showEditPage(model, id);
    }

    @PostMapping("/edit")
    public String editApartment(Model model,
                            @RequestParam Long id,
                            ApartmentDTO apartmentDTO,
                            BindingResult bindingResult) {
        return apartmentService.editApartment(model, id, apartmentDTO, bindingResult);
    }

    @GetMapping("/delete")
    public String deleteApartmentById(@RequestParam Long id) {
        return apartmentService.deleteApartmentById(id);
    }

    /*
    @GetMapping("/getApartmentPlan")
    public String getApartmentPlan(Model model, @RequestParam Long id) {
        return apartmentService.getApartmentPlan(model, id);
    }
     */


}
