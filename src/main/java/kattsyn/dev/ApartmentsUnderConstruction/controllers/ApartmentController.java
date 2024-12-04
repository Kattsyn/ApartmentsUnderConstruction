package kattsyn.dev.ApartmentsUnderConstruction.controllers;

import kattsyn.dev.ApartmentsUnderConstruction.dtos.ApartmentDTO;
import kattsyn.dev.ApartmentsUnderConstruction.service.ApartmentService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/byFloorId")
    public String showApartmentsListByFloorId(Model model, @RequestParam Long floorId){
        return apartmentService.showApartmentsListByFloorId(model, floorId);
    }


    @GetMapping({"", "/"})
    public String showApartmentsList(Model model) {
        return apartmentService.showApartmentsList(model);
    }


    @GetMapping( "/pages")
    public String showApartmentsListPage(
            Model model,
            @RequestParam (defaultValue = "0") int pageNumber,
            @RequestParam (defaultValue = "2") int count) {
        return apartmentService.showApartmentsListPage(model, pageNumber, count);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/create")
    public String showCreatePage(Model model) {
        return apartmentService.showCreatePage(model);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PostMapping("/create")
    public String createApartment(@Valid @ModelAttribute("apartmentDTO") ApartmentDTO apartmentDTO, BindingResult bindingResult) {
        return apartmentService.createApartment(apartmentDTO, bindingResult);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/edit")
    public String showEditPage(Model model, @RequestParam Long id) {
        return apartmentService.showEditPage(model, id);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
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


}
