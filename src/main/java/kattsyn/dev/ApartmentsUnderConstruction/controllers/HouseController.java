package kattsyn.dev.ApartmentsUnderConstruction.controllers;

import kattsyn.dev.ApartmentsUnderConstruction.dtos.HouseDTO;
import kattsyn.dev.ApartmentsUnderConstruction.service.HouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/houses")
@Slf4j
public class HouseController {

    private final HouseService houseService;


    @GetMapping({"", "/"})
    public String showHouseList(Model model) {
        return houseService.showHouseList(model);
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        return houseService.showCreatePage(model);
    }

    @PostMapping("/create")
    public String createHouse(@Valid @ModelAttribute("houseDTO") HouseDTO houseDTO, BindingResult bindingResult) {
        return houseService.createHouse(houseDTO, bindingResult);
    }

    @GetMapping("/edit")
    public String showEditPage(Model model, @RequestParam Long id) {
        return houseService.showEditPage(model, id);
    }

    @PostMapping("/edit")
    public String editHouse(Model model,
                            @RequestParam Long id,
                            HouseDTO houseDTO,
                            BindingResult bindingResult) {
        return houseService.editHouse(model, id, houseDTO, bindingResult);
    }


    @GetMapping("/delete")
    public String deleteHouseById(@RequestParam Long id) {
        return houseService.deleteHouseById(id);
    }
}
