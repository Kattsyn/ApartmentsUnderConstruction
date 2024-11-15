package kattsyn.dev.ApartmentsUnderConstruction.service;

import kattsyn.dev.ApartmentsUnderConstruction.dtos.HouseDTO;
import kattsyn.dev.ApartmentsUnderConstruction.entities.House;
import kattsyn.dev.ApartmentsUnderConstruction.repositories.HouseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class HouseService {

    private final HouseRepository houseRepository;

    public List<House> findAll() {
        return houseRepository.findAll(Sort.by(Sort.Direction.DESC, "houseId"));
    }

    public String showCreatePage(Model model) {
        HouseDTO houseDTO = new HouseDTO();
        model.addAttribute("houseDTO", houseDTO);
        return "houses/create-house";
    }

    public String showHouseList(Model model) {
        List<House> houses = findAll();
        model.addAttribute("houses", houses);
        return "houses/index";
    }

    public String showEditPage(Model model, @RequestParam Long id) {
        Optional<House> house = houseRepository.findById(id);
        if (house.isEmpty()) {
            log.error("House id: {} not found", id);
            return "redirect:/houses";
        }
        model.addAttribute("house", house.get());

        HouseDTO houseDTO = new HouseDTO();
        houseDTO.setName(house.get().getName());
        houseDTO.setAddress(house.get().getAddress());
        houseDTO.setBuildingStartDate(house.get().getBuildingStartDate());
        houseDTO.setPlannedBuildingEndDate(house.get().getPlannedBuildingEndDate());
        houseDTO.setCommissioningDate(house.get().getCommissioningDate());

        model.addAttribute("houseDTO", houseDTO);

        return "houses/edit-house";
    }

    public String editHouse(Model model,
                            Long id,
                            HouseDTO houseDTO,
                            BindingResult bindingResult) {
        Optional<House> house = houseRepository.findById(id);
        if (house.isEmpty()) {
            log.error("House id: {} not found in editHouse method.", id);
            return "redirect:/houses";
        }
        model.addAttribute("house", house.get());
        if (bindingResult.hasErrors()) {
            return "houses/edit-house";
        }
        /*
        //Если бы была новая картинка, то...
        //deleting old image
        if (!houseDTO.getImageFile().isEmpty()) {
            String uploadDir = "public/images/";
            Path oldImagePath = Paths.get(uploadDir + house.getImageFileName());

            try {
                Files.delete(oldImagePath);
            } catch (Exception e) {
                System.out.println("Exception: " + e.getMessage());
            }
            //saving new file
            MultipartFile image = houseDTO.getImageFile();
            Date createdAt = new Date();
            String storageFileName = createdAt.getTime() + "_" + image.getOriginalFile();

            try (InputStream inputStream = image.getInputStream()) {
                Files.copy(inputStream, Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
            }

            house.setImageFileName(storageFileName);
        }
        */
        house.get().setAddress(houseDTO.getAddress());
        house.get().setName(houseDTO.getName());
        house.get().setBuildingStartDate(houseDTO.getBuildingStartDate());
        house.get().setPlannedBuildingEndDate(houseDTO.getPlannedBuildingEndDate());
        house.get().setCommissioningDate(houseDTO.getCommissioningDate());

        houseRepository.save(house.get());

        return "redirect:/houses";
    }


    public String createHouse(HouseDTO houseDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "houses/create-house";
        }
        save(houseDTO);
        return "redirect:/houses";
    }

    public String deleteHouseById(Long id) {
        Optional<House> house = houseRepository.findById(id);

        if (house.isPresent()) {
            houseRepository.delete(house.get());
        } else {
            log.error("House id: {} in method deleteHouseById() NOT FOUND", id);
        }

        return "redirect:/houses";
    }

    public House save(HouseDTO houseDTO) {
        House house = new House(
                houseDTO.getAddress(),
                houseDTO.getName(),
                houseDTO.getBuildingStartDate(),
                houseDTO.getPlannedBuildingEndDate(),
                houseDTO.getCommissioningDate());

        return houseRepository.save(house);
    }
}
