package kattsyn.dev.ApartmentsUnderConstruction.controllers;

import kattsyn.dev.ApartmentsUnderConstruction.dtos.HouseDTO;
import kattsyn.dev.ApartmentsUnderConstruction.entities.House;
import kattsyn.dev.ApartmentsUnderConstruction.service.HouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

        /*
        if(houseDTO.getCommissioningDate().isBefore(houseDTO.getPlannedBuildingEndDate())) {
            bindingResult.addError(new FieldError("houseDTO", "commissioningDate", "Commissioning date cant be earlier than building end date."));
        }
        В ручную добавление ошибки в bindingResult todo: DELETE
         */
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
        houseService.deleteHouseById(id);
        return "redirect:/houses";
    }

}
