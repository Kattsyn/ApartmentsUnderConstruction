package kattsyn.dev.ApartmentsUnderConstruction.controllers;

import kattsyn.dev.ApartmentsUnderConstruction.dtos.FloorDTO;
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

    @GetMapping({"", "/"})
    public String showFloorsList(Model model) {
        return floorService.showFloorsList(model);
    }

    @GetMapping("/getFloorPlan")
    public String getFloorPlan(Model model, @RequestParam Long id) {
        return floorService.getFloorPlan(model, id);
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        return floorService.showCreatePage(model);
    }

    @PostMapping("/create")
    public String createHouse(@Valid @ModelAttribute("floorDTO") FloorDTO floorDTO, BindingResult bindingResult) {
        return floorService.createFloor(floorDTO, bindingResult);
    }

    @GetMapping("/edit")
    public String showEditPage(Model model, @RequestParam Long id) {
        return floorService.showEditPage(model, id);
    }

    @PostMapping("/edit")
    public String editFloor(Model model,
                            @RequestParam Long id,
                            FloorDTO floorDTO,
                            BindingResult bindingResult) {
        return floorService.editFloor(model, id, floorDTO, bindingResult);
    }

    @GetMapping("/delete")
    public String deleteFloorById(@RequestParam Long id) {
        return floorService.deleteFloorById(id);
    }
}
