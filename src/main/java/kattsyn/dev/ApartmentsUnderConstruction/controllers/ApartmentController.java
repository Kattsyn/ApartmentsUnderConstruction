package kattsyn.dev.ApartmentsUnderConstruction.controllers;

import kattsyn.dev.ApartmentsUnderConstruction.dtos.ApartmentDTO;
import kattsyn.dev.ApartmentsUnderConstruction.dtos.ApartmentReservationRequestDTO;
import kattsyn.dev.ApartmentsUnderConstruction.dtos.filters.ApartmentFilter;
import kattsyn.dev.ApartmentsUnderConstruction.entities.Apartment;
import kattsyn.dev.ApartmentsUnderConstruction.mappers.ApartmentMapper;
import kattsyn.dev.ApartmentsUnderConstruction.service.*;
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
    private final SaleStatusService statusService;
    private final ApartmentReservationService reservationService;
    private final ApartmentMapper apartmentMapper;

    @GetMapping("/info")
    public String showInfoPage(Model model, @RequestParam Long id) {
        model.addAttribute("apartment", apartmentService.findById(id));
        model.addAttribute("reservationRequest", new ApartmentReservationRequestDTO());
        return "apartments/info-apartment";
    }

    @PostMapping("/reserveApartment")
    public String reserveApartment(@ModelAttribute("reservationRequest") ApartmentReservationRequestDTO reservationRequestDTO) {
        System.out.println(reservationRequestDTO);
        reservationService.save(reservationRequestDTO);
        return "redirect:/apartments/";
    }


    @GetMapping({"/"})
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
        model.addAttribute("regions", regionService.findAll());
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
        model.addAttribute("apartmentDTO", new ApartmentDTO());
        model.addAttribute("saleStatuses", statusService.findAll());
        return "apartments/create-apartment";
    }

    @Secured("ROLE_MANAGER")
    @PostMapping("/create")
    public String createApartment(@Valid @ModelAttribute("apartmentDTO") ApartmentDTO apartmentDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "apartments/create-apartment";
        }
        apartmentService.save(apartmentDTO);
        return "redirect:/apartments/";
    }

    @Secured("ROLE_MANAGER")
    @GetMapping("/edit")
    public String showEditPage(Model model, @RequestParam Long id) {
        Apartment apartment = apartmentService.findById(id);
        ApartmentDTO apartmentDTO = apartmentMapper.toApartmentDTO(apartment);

        model.addAttribute("apartment", apartment);
        model.addAttribute("apartmentDTO", apartmentDTO);
        model.addAttribute("saleStatuses", statusService.findAll());

        return "apartments/edit-apartment";
    }

    @Secured("ROLE_MANAGER")
    @PostMapping("/edit")
    public String editApartment(Model model,
                                @RequestParam Long id,
                                ApartmentDTO apartmentDTO,
                                BindingResult bindingResult) {
        Apartment apartment = apartmentService.findById(id);
        model.addAttribute("apartment", apartment);

        if (bindingResult.hasErrors()) {
            return "apartments/edit-apartment";
        }

        apartmentService.save(apartmentDTO);
        return "redirect:/apartments/";
    }

    @Secured("ROLE_MANAGER")
    @GetMapping("/delete")
    public String deleteApartmentById(@RequestParam Long id) {
        apartmentService.deleteApartmentById(id);
        return "redirect:/apartments/";
    }


}
