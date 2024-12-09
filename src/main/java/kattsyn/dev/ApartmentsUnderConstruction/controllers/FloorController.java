package kattsyn.dev.ApartmentsUnderConstruction.controllers;

import kattsyn.dev.ApartmentsUnderConstruction.dtos.FloorDTO;
import kattsyn.dev.ApartmentsUnderConstruction.entities.Floor;
import kattsyn.dev.ApartmentsUnderConstruction.mappers.FloorMapper;
import kattsyn.dev.ApartmentsUnderConstruction.service.FloorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/floors")
@AllArgsConstructor
@Controller
public class FloorController {

    private final FloorService floorService;
    private final FloorMapper floorMapper;

    @GetMapping({"", "/"})
    public String showFloorsList(
            Model model,
            @RequestParam (defaultValue = "0") int pageNumber,
            @RequestParam (defaultValue = "10") int count) {
        return floorService.showFloorsList(model, pageNumber, count);
    }

    @GetMapping("/getFloorPlan")
    public String getFloorPlan(Model model, @RequestParam Long id) {
        model.addAttribute("imageLink", floorService.getFloorPlanUrl(id));
        return "images/index";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        model.addAttribute("floorDTO", new FloorDTO());
        return "floors/create-floor";
    }

    @PostMapping("/create")
    public String createHouse(@Valid @ModelAttribute("floorDTO") FloorDTO floorDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "floors/create-floor";
        }
        try {
            floorService.save(floorDTO);
        } catch (IllegalArgumentException exception) {
            return "floors/create-floor";
        }
        return "redirect:/floors";
    }

    @GetMapping("/edit")
    public String showEditPage(Model model, @RequestParam Long id) {
        Floor floor = floorService.findById(id);

        model.addAttribute("floor", floor);
        model.addAttribute("floorDTO", floorMapper.toFloorDTO(floor));

        return "floors/edit-floor";
    }

    @PostMapping("/edit")
    public String editFloor(Model model,
                            @RequestParam Long id,
                            FloorDTO floorDTO,
                            BindingResult bindingResult) {
        Floor floor = floorService.findById(id);

        if (bindingResult.hasErrors()) {
            model.addAttribute("floor", floor);
            return "floors/edit-floor";
        }

        floorService.editFloor(id, floorDTO);

        return "redirect:/floors";
    }

    @GetMapping("/delete")
    public String deleteFloorById(@RequestParam Long id) {
        floorService.deleteFloorById(id);
        return "redirect:/floors";
    }
}
