package kattsyn.dev.ApartmentsUnderConstruction.service;


import kattsyn.dev.ApartmentsUnderConstruction.dtos.FloorDTO;
import kattsyn.dev.ApartmentsUnderConstruction.entities.Floor;
import kattsyn.dev.ApartmentsUnderConstruction.entities.House;
import kattsyn.dev.ApartmentsUnderConstruction.repositories.FloorRepository;
import kattsyn.dev.ApartmentsUnderConstruction.repositories.HouseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
public class FloorService {

    private final FloorRepository floorRepository;
    private final HouseRepository houseRepository;

    public Floor save(FloorDTO floorDTO) {

        Floor floor = new Floor(
                floorDTO.getFloorNumber(),
                floorDTO.getFloorPlan());

        if (floorDTO.getHouseId() != null) {
            Optional<House> house = houseRepository.findById(floorDTO.getHouseId());
            if (house.isPresent()) {
                floor.setHouse(house.get());
            } else {
                throw new IllegalArgumentException("House id: {} in method FloorService.save() NOT FOUND");
            }
        }

        return floorRepository.save(floor);
    }

    public String showFloorsList(Model model, int pageNumber, int count) {
        Page<Floor> floors = floorRepository.findAll(PageRequest.of(pageNumber, count, Sort.by("floor_id")));
        model.addAttribute("floors", floors.getContent());
        return "floors/index";
    }

    public String getFloorPlan(Model model, Long id) {
        Optional<Floor> floor = floorRepository.findById(id);

        if (floor.isEmpty()) {
            log.error("Floor id: {} in getFlorPlan() method NOT FOUND.", id);
            return "floors/index";
        }
        model.addAttribute("imageLink", floor.get().getFloorPlan());
        return "images/index";
    }

    public String showCreatePage(Model model) {
        FloorDTO floorDTO = new FloorDTO();
        model.addAttribute("floorDTO", floorDTO);
        return "floors/create-floor";
    }

    public String createFloor(@Valid @ModelAttribute("houseDTO") FloorDTO floorDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "floors/create-floor";
        }
        try {
            save(floorDTO);
        } catch (IllegalArgumentException exception) {
            log.error(exception.getMessage());
            return "floors/create-floor";
        }
        return "redirect:/floors";
    }


    public String showEditPage(Model model, @RequestParam Long id) {
        Optional<Floor> floor = floorRepository.findById(id);

        if (floor.isEmpty()) {
            log.error("Floor id: {} in showEditPage() method NOT FOUND.", id);
            return "redirect:/floors";
        }
        model.addAttribute("floor", floor);

        FloorDTO floorDTO = new FloorDTO();

        floorDTO.setFloorNumber(floor.get().getFloorNumber());
        floorDTO.setFloorPlan(floor.get().getFloorPlan());
        if (floor.get().getHouse() != null) {
            floorDTO.setHouseId(null);
        }

        model.addAttribute("floorDTO", floorDTO);

        return "floors/edit-floor";
    }

    public String editFloor(Model model,
                            @RequestParam Long id,
                            FloorDTO floorDTO,
                            BindingResult bindingResult) {
        Optional<Floor> floor = floorRepository.findById(id);

        if (floor.isEmpty()) {
            log.error("Floor id: {} in editFloor() NOT FOUND", id);
            return "redirect:/floors";
        }
        model.addAttribute("floor", floor);
        if (bindingResult.hasErrors()) {
            return "floors/edit-floor";
        }

        floor.get().setFloorNumber(floorDTO.getFloorNumber());
        floor.get().setFloorPlan(floorDTO.getFloorPlan());

        if (floorDTO.getHouseId() != null) {
            Optional<House> house = houseRepository.findById(floorDTO.getHouseId());
            if (house.isPresent()) {
                floor.get().setHouse(house.get());
            } else {
                //throw new IllegalArgumentException("House id: " + id +" in method editFloor() NOT FOUND");
                log.error("House id: {} in method editFloor NOT FOUND", floorDTO.getHouseId());
            }
        }

        floorRepository.save(floor.get());
        return "redirect:/floors";
    }

    public String deleteFloorById(Long id) {
        Optional<Floor> floor = floorRepository.findById(id);

        if (floor.isPresent()) {
            floorRepository.delete(floor.get());
        } else {
            log.error("Floor id: {} in method deleteFloorById() NOT FOUND", id);
        }

        return "redirect:/floors";
    }

}
