package kattsyn.dev.ApartmentsUnderConstruction.controllers;

import kattsyn.dev.ApartmentsUnderConstruction.dtos.ApartmentDTO;
import kattsyn.dev.ApartmentsUnderConstruction.dtos.filters.ApartmentFilter;
import kattsyn.dev.ApartmentsUnderConstruction.entities.Apartment;
import kattsyn.dev.ApartmentsUnderConstruction.service.ApartmentService;
import kattsyn.dev.ApartmentsUnderConstruction.service.FloorService;
import kattsyn.dev.ApartmentsUnderConstruction.service.HouseService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/apartments")
@AllArgsConstructor
public class ApartmentController {

    private final ApartmentService apartmentService;
    private final HouseService houseService;
    private final FloorService floorService;

    @GetMapping("/info")
    public String showInfoPage(Model model, @RequestParam Long id) {
        return apartmentService.showInfoPage(model, id);
    }

    @GetMapping("/byFloorId")
    public String showApartmentsListByFloorId(Model model, @RequestParam Long floorId) {
        return apartmentService.showApartmentsListByFloorId(model, floorId);
    }

    @GetMapping({"/", ""})
    public String getApartmentsPageWithPaginationAndFiltering(
            Model model,
            ApartmentFilter filter,
            @RequestParam(defaultValue = "0") int currentPage,
            @RequestParam(defaultValue = "5") int count
    ) {


        Page<Apartment> apartmentsPage = apartmentService.getFilteredApartmentPage(filter, currentPage, count);

        model.addAttribute("filter", filter);
        model.addAttribute("apartments", apartmentsPage.getContent());
        model.addAttribute("distinctApartmentsByAmountOfRooms", apartmentService.findDistinctAmountOfRooms());
        model.addAttribute("housesNames", houseService.getDistinctHousesNames());
        model.addAttribute("floorNumbers", floorService.getDistinctFloorNumbers());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", apartmentsPage.getTotalPages());

        List<Integer> pageNumbers = IntStream.range(0, apartmentsPage.getTotalPages())
                .boxed()
                .collect(Collectors.toList());
        model.addAttribute("pageNumbers", pageNumbers);

        return "apartments/index";
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
