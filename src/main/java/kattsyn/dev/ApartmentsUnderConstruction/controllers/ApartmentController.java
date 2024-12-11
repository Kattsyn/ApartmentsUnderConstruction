package kattsyn.dev.ApartmentsUnderConstruction.controllers;

import kattsyn.dev.ApartmentsUnderConstruction.dtos.ApartmentDTO;
import kattsyn.dev.ApartmentsUnderConstruction.dtos.filters.ApartmentFilter;
import kattsyn.dev.ApartmentsUnderConstruction.entities.Apartment;
import kattsyn.dev.ApartmentsUnderConstruction.service.ApartmentService;
import kattsyn.dev.ApartmentsUnderConstruction.service.FloorService;
import kattsyn.dev.ApartmentsUnderConstruction.service.HouseService;
import kattsyn.dev.ApartmentsUnderConstruction.service.RegionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
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
    private final RegionService regionService;

    @GetMapping("/info")
    public String showInfoPage(Model model, @RequestParam Long id) {
        return apartmentService.showInfoPage(model, id);
    }


    @GetMapping({"/", ""})
    public String getApartmentsPageWithPaginationAndFiltering(
            Model model,
            ApartmentFilter filter,
            @RequestParam(defaultValue = "0") int currentPage,
            @RequestParam(defaultValue = "6") int pageSize
    ) {


        Page<Apartment> apartmentsPage = apartmentService.getFilteredApartmentPage(filter, currentPage, pageSize);

        model.addAttribute("filter", filter);
        model.addAttribute("apartments", apartmentsPage.getContent());
        model.addAttribute("distinctApartmentsByAmountOfRooms", apartmentService.findDistinctAmountOfRooms());
        model.addAttribute("housesNames", houseService.getDistinctHousesNames());
        model.addAttribute("floorNumbers", floorService.getDistinctFloorNumbers());
        model.addAttribute("regionsNames", regionService.getDistinctRegionsNames());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", apartmentsPage.getTotalPages());

        List<Integer> pageNumbers = IntStream.range(0, apartmentsPage.getTotalPages())
                .boxed()
                .collect(Collectors.toList());
        model.addAttribute("pageNumbers", pageNumbers);

        return "apartments/index";
    }


    @Secured("ROLE_MANAGER")
    @GetMapping("/create")
    public String showCreatePage(Model model) {
        return apartmentService.showCreatePage(model);
    }

    @Secured("ROLE_MANAGER")
    @PostMapping("/create")
    public String createApartment(@Valid @ModelAttribute("apartmentDTO") ApartmentDTO apartmentDTO, BindingResult bindingResult) {
        return apartmentService.createApartment(apartmentDTO, bindingResult);
    }

    @Secured("ROLE_MANAGER")
    @GetMapping("/edit")
    public String showEditPage(Model model, @RequestParam Long id) {
        return apartmentService.showEditPage(model, id);
    }

    @Secured("ROLE_MANAGER")
    @PostMapping("/edit")
    public String editApartment(Model model,
                                @RequestParam Long id,
                                ApartmentDTO apartmentDTO,
                                BindingResult bindingResult) {
        return apartmentService.editApartment(model, id, apartmentDTO, bindingResult);
    }

    @Secured("ROLE_MANAGER")
    @GetMapping("/delete")
    public String deleteApartmentById(@RequestParam Long id) {
        return apartmentService.deleteApartmentById(id);
    }


}
