package kattsyn.dev.ApartmentsUnderConstruction.service;

import kattsyn.dev.ApartmentsUnderConstruction.dtos.ApartmentDTO;
import kattsyn.dev.ApartmentsUnderConstruction.dtos.filters.ApartmentFilter;
import kattsyn.dev.ApartmentsUnderConstruction.entities.Apartment;
import kattsyn.dev.ApartmentsUnderConstruction.entities.Floor;
import kattsyn.dev.ApartmentsUnderConstruction.entities.SaleStatus;
import kattsyn.dev.ApartmentsUnderConstruction.repositories.ApartmentRepository;
import kattsyn.dev.ApartmentsUnderConstruction.repositories.FloorRepository;
import kattsyn.dev.ApartmentsUnderConstruction.repositories.StatusRepository;
import kattsyn.dev.ApartmentsUnderConstruction.specifications.ApartmentSpecification;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;
    private final FloorRepository floorRepository;
    private final StatusRepository statusRepository;

    public String findAllByFilter(Model model, ApartmentFilter filter) {
        //model.addAttribute("apartments", apartmentRepository.findAllByFilter(filter));
        ApartmentSpecification specification = new ApartmentSpecification(filter);
        log.info(filter.toString());
        model.addAttribute("apartments", apartmentRepository.findAll(specification));
        model.addAttribute("distinctApartmentsByAmountOfRooms", apartmentRepository.findDistinctRooms());

        return "apartments/new-index-test";
    }

    public List<Byte> findDistinctAmountOfRooms() {
        return apartmentRepository.findDistinctRooms();
    }

    public Page<Apartment> getFilteredApartmentPage(ApartmentFilter filter, int pageNumber, int count) {
        ApartmentSpecification specification = new ApartmentSpecification(filter);
        log.info(filter.toString());

        Pageable page = PageRequest.of(pageNumber, count);
        return apartmentRepository.findAll(specification, page);
    }

    public String showInfoPage(Model model, Long id) {
        Optional<Apartment> apartment = apartmentRepository.findById(id);
        if (apartment.isEmpty()) {
            log.error("Apartment with id {} not found", id);
            return "redirect:/apartments";
        }
        model.addAttribute("apartment", apartment.get());
        return "apartments/info-apartment";
    }

    public String showApartmentsListPage(Model model, int pageNumber, int count) {
        Page<Apartment> page = apartmentRepository.findAll(PageRequest.of(pageNumber, count, Sort.by("floor.house.name")));
        model.addAttribute("apartments", page.getContent());
        model.addAttribute("distinctApartmentsByAmountOfRooms", apartmentRepository.findDistinctRooms());

        return "apartments/new-index-test";
    }

    public String showApartmentsListByFloorId(Model model, @RequestParam Long floorId) {
        Optional<Floor> floor = floorRepository.findById(floorId);
        if (floor.isEmpty()) {
            log.error("Floor id: {} NOT FOUND in showApartmentsListByFloorId", floorId);
            return "redirect:/floors";
        }

        List<Apartment> list = apartmentRepository.findByFloor(floor.get());
        model.addAttribute("apartments", list);
        return "apartments/index";
    }

    public String showApartmentsList(Model model) {
        List<Apartment> apartments = apartmentRepository.findAll(Sort.by("floor.house.name"));
        model.addAttribute("apartments", apartments);
        return "apartments/index";
    }

    public String showCreatePage(Model model) {

        List<SaleStatus> saleStatuses = statusRepository.findAll();

        ApartmentDTO apartmentDTO = new ApartmentDTO();
        model.addAttribute("apartmentDTO", apartmentDTO);
        model.addAttribute("saleStatuses", saleStatuses);
        return "apartments/create-apartment";

    }

    public Apartment save(ApartmentDTO apartmentDTO) {
        Apartment apartment = new Apartment(
                apartmentDTO.getApartmentNumber(),
                apartmentDTO.getTotalArea(),
                apartmentDTO.getLivingArea(),
                apartmentDTO.getAmountOfRooms(),
                apartmentDTO.getEntranceNumber(),
                apartmentDTO.getApartmentCost(),
                apartmentDTO.getApartmentPlan()
        );

        if (apartmentDTO.getFloorId() != null) {
            Optional<Floor> floor = floorRepository.findById(apartmentDTO.getFloorId());
            if (floor.isPresent()) {
                apartment.setFloor(floor.get());
            } else {
                throw new IllegalArgumentException("Floor id:" + apartmentDTO.getFloorId() + "in ApartmentService.save method NOT FOUND");
            }
        }
        if (apartmentDTO.getStatusId() != null) {
            Optional<SaleStatus> saleStatus = statusRepository.findById(apartmentDTO.getStatusId());
            if (saleStatus.isPresent()) {
                apartment.setStatus(saleStatus.get());
            } else {
                throw new IllegalArgumentException("Sale Status id:" + apartmentDTO.getStatusId() + "in ApartmentService.save method NOT FOUND");
            }
        }

        return apartmentRepository.save(apartment);
    }

    public String createApartment(@Valid @ModelAttribute("apartmentDTO") ApartmentDTO apartmentDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "apartments/create-apartment";
        }
        try {
            save(apartmentDTO);
        } catch (IllegalArgumentException exception) {
            log.error(exception.getMessage());
            return "apartments/create-apartment";
        }
        return "redirect:/apartments";

    }

    public String showEditPage(Model model, @RequestParam Long id) {

        Optional<Apartment> apartment = apartmentRepository.findById(id);
        List<SaleStatus> saleStatuses = statusRepository.findAll();

        if (apartment.isEmpty()) {
            log.error("Apartment id: {} in showEditPage() method NOT FOUND.", id);
            return "redirect:/apartments";
        }
        model.addAttribute("apartment", apartment.get());

        ApartmentDTO apartmentDTO = new ApartmentDTO();

        apartmentDTO.setApartmentNumber(apartment.get().getApartmentNumber());
        apartmentDTO.setTotalArea(apartment.get().getTotalArea());
        apartmentDTO.setLivingArea(apartment.get().getLivingArea());
        apartmentDTO.setAmountOfRooms(apartment.get().getAmountOfRooms());
        apartmentDTO.setEntranceNumber(apartment.get().getEntranceNumber());
        apartmentDTO.setApartmentCost(apartment.get().getApartmentCost());
        apartmentDTO.setApartmentPlan(apartment.get().getApartmentPlan());

        if (apartment.get().getFloor() != null) {
            apartmentDTO.setFloorId(apartment.get().getFloor().getFloorId());
        }

        if (apartment.get().getStatus() != null) {
            apartmentDTO.setStatusId(apartment.get().getStatus().getSaleStatusId());
        }

        model.addAttribute("apartmentDTO", apartmentDTO);
        model.addAttribute("saleStatuses", saleStatuses);

        return "apartments/edit-apartment";


    }

    public String editApartment(Model model,
                                @RequestParam Long id,
                                ApartmentDTO apartmentDTO,
                                BindingResult bindingResult) {

        Optional<Apartment> apartment = apartmentRepository.findById(id);

        if (apartment.isEmpty()) {
            log.error("Apartment id: {} in editApartment() NOT FOUND", id);
            return "redirect:/apartments";
        }

        model.addAttribute("apartment", apartment);
        if (bindingResult.hasErrors()) {
            return "apartments/edit-apartment";
        }

        apartment.get().setApartmentNumber(apartmentDTO.getApartmentNumber());
        apartment.get().setTotalArea(apartmentDTO.getTotalArea());
        apartment.get().setLivingArea(apartmentDTO.getLivingArea());
        apartment.get().setAmountOfRooms(apartmentDTO.getAmountOfRooms());
        apartment.get().setEntranceNumber(apartmentDTO.getEntranceNumber());
        apartment.get().setApartmentCost(apartmentDTO.getApartmentCost());
        apartment.get().setApartmentPlan(apartmentDTO.getApartmentPlan());

        if (apartmentDTO.getFloorId() != null) {
            Optional<Floor> floor = floorRepository.findById(apartmentDTO.getFloorId());
            if (floor.isPresent()) {
                apartment.get().setFloor(floor.get());
            } else {
                log.error("Floor id: {} in method editApartment NOT FOUND", apartmentDTO.getFloorId());
            }
        }

        if (apartmentDTO.getStatusId() != null) {
            Optional<SaleStatus> status = statusRepository.findById(apartmentDTO.getStatusId());
            if (status.isPresent()) {
                apartment.get().setStatus(status.get());
            } else {
                log.error("Sale Status id: {} in method editApartment NOT FOUND", apartmentDTO.getStatusId());
            }
        }

        apartmentRepository.save(apartment.get());
        return "redirect:/apartments";
    }

    public String deleteApartmentById(Long id) {
        Optional<Apartment> apartment = apartmentRepository.findById(id);

        if (apartment.isPresent()) {
            apartmentRepository.delete(apartment.get());
        } else {
            log.error("Apartment id: {} in method deleteApartmentById() NOT FOUND", id);
        }
        return "redirect:/apartments";
    }

}
