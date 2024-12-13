package kattsyn.dev.ApartmentsUnderConstruction.service;

import kattsyn.dev.ApartmentsUnderConstruction.dtos.HouseDTO;
import kattsyn.dev.ApartmentsUnderConstruction.dtos.filters.HouseFilter;
import kattsyn.dev.ApartmentsUnderConstruction.entities.House;
import kattsyn.dev.ApartmentsUnderConstruction.entities.Image;
import kattsyn.dev.ApartmentsUnderConstruction.mappers.HouseMapper;
import kattsyn.dev.ApartmentsUnderConstruction.repositories.HouseRepository;
import kattsyn.dev.ApartmentsUnderConstruction.specifications.HouseSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HouseService {

    private final HouseRepository houseRepository;
    private final ImageService imageService;
    private final HouseMapper houseMapper;

    public Integer getMinApartmentPriceByHouseId(Long id) {
        return houseRepository.getMinApartmentPriceByHouseId(id);
    }

    public Page<House> getFilteredHousePage(HouseFilter filter, int pageNumber, int pageSize) {
        return houseRepository.findAll(
                new HouseSpecification(filter),
                PageRequest.of(pageNumber, pageSize)
        );
    }

    public void saveWithImages(HouseDTO houseDTO, List<String> images) {
        List<Image> savedImages = imageService.saveAllByUrls(images);

        House house = houseMapper.fromHouseDTO(houseDTO);
        house.setImages(savedImages);
        save(house);
    }

    public House findById(Long id) {
        return houseRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException(String.format("House id: %s NOT FOUND", id)));
    }

    public List<String> getDistinctHousesNames() {
        return houseRepository.getDistinctNames();
    }


    public void deleteHouseById(Long id) {
        houseRepository.delete(findById(id));
    }

    public void save(House house) {
        houseRepository.save(house);
    }
}
