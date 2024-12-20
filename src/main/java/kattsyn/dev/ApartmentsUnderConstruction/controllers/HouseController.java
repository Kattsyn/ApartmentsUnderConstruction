package kattsyn.dev.ApartmentsUnderConstruction.controllers;

import kattsyn.dev.ApartmentsUnderConstruction.dtos.HouseDTO;
import kattsyn.dev.ApartmentsUnderConstruction.dtos.filters.HouseFilter;
import kattsyn.dev.ApartmentsUnderConstruction.entities.House;
import kattsyn.dev.ApartmentsUnderConstruction.mappers.HouseMapper;
import kattsyn.dev.ApartmentsUnderConstruction.service.HouseService;
import kattsyn.dev.ApartmentsUnderConstruction.service.RegionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
@RequestMapping("/houses")
@Slf4j
public class HouseController {

    private final HouseService houseService;
    private final RegionService regionService;
    private final HouseMapper houseMapper;


    @GetMapping({ "/"})
    public String showHouseList(
            Model model,
            HouseFilter filter,
            @RequestParam(defaultValue = "0") int currentPage,
            @RequestParam(defaultValue = "6") int pageSize) {
        Page<House> housePage = houseService.getFilteredHousePage(filter, currentPage, pageSize);
        List<House> housesWithMinPrices = housePage.getContent();
        housesWithMinPrices.forEach(house -> house.setMinApartmentPrice(houseService.getMinApartmentPriceByHouseId(house.getHouseId())));
        model.addAttribute("houses", housePage.getContent());
        model.addAttribute("filter", filter);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("regions", regionService.findAll());
        model.addAttribute("totalPages", housePage.getTotalPages());

        List<Integer> pageNumbers = IntStream.range(0, housePage.getTotalPages())
                .boxed()
                .collect(Collectors.toList());
        model.addAttribute("pageNumbers", pageNumbers);

        return "houses/index";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        model.addAttribute("houseDTO", new HouseDTO());
        model.addAttribute("regions", regionService.findAll());
        return "houses/create-house";
    }

    @PostMapping("/create")
    public String createHouse(@Valid @ModelAttribute("houseDTO") HouseDTO houseDTO, @RequestParam List<String> images, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "houses/create-house";
        }
        houseService.saveWithImages(houseDTO, images);
        houseService.save(houseMapper.fromHouseDTO(houseDTO));
        return "redirect:/houses/";
    }

    @GetMapping("/edit")
    public String showEditPage(Model model, @RequestParam Long id) {
        House house = houseService.findById(id);

        model.addAttribute("house", house);
        model.addAttribute("houseDTO", houseMapper.toHouseDTO(house));

        return "houses/edit-house";
    }

    @PostMapping("/edit")
    public String editHouse(Model model,
                            @RequestParam Long id,
                            HouseDTO houseDTO,
                            BindingResult bindingResult) {
        House house = houseService.findById(id);

        model.addAttribute("house", house);
        if (bindingResult.hasErrors()) {
            return "houses/edit-house";
        }

        houseService.save(houseMapper.fromHouseDTO(houseDTO));

        return "redirect:/houses/";
    }


    @GetMapping("/delete")
    public String deleteHouseById(@RequestParam Long id) {
        houseService.deleteHouseById(id);
        return "redirect:/houses/";
    }
}
