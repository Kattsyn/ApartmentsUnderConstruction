package kattsyn.dev.ApartmentsUnderConstruction.service;


import kattsyn.dev.ApartmentsUnderConstruction.dtos.FloorDTO;
import kattsyn.dev.ApartmentsUnderConstruction.entities.Floor;
import kattsyn.dev.ApartmentsUnderConstruction.entities.House;
import kattsyn.dev.ApartmentsUnderConstruction.mappers.FloorMapper;
import kattsyn.dev.ApartmentsUnderConstruction.repositories.FloorRepository;
import kattsyn.dev.ApartmentsUnderConstruction.repositories.HouseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class FloorService {

    private final FloorRepository floorRepository;
    private final HouseRepository houseRepository;
    private final FloorMapper floorMapper;

    @Transactional
    public List<Byte> getDistinctFloorNumbers() {
        return floorRepository.distinctFloorNumbers();
    }

    @Transactional
    public void save(FloorDTO floorDTO) {
        Floor floor = floorMapper.fromFloorDTO(floorDTO);

        if (floorDTO.getHouseId() != null) {
            House house = houseRepository.findById(floorDTO.getHouseId()).orElseThrow(
                    () -> new IllegalArgumentException(String.format(
                            "House id: %s in method save() NOT FOUND", floorDTO.getHouseId()
                    ))
            );
            floor.setHouse(house);
        }

        floorRepository.save(floor);
    }

    @Transactional
    public Page<Floor> getFloorPage(int pageNumber, int pageSize) {
        return floorRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by("floorId")));
    }

    @Transactional
    public String getFloorPlanUrl(Long id) {
        Floor floor = floorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                String.format("Floor id: %s in getFloorPlanUrl() method NOT FOUND", id)
        ));

        return floor.getFloorPlan();
    }


    @Transactional
    public Floor findById(Long id) {
        return floorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(String.format(
                "Floor id: %s in findById() method NOT FOUND", id
        )));
    }

    @Transactional
    public void editFloor(Long id, FloorDTO floorDTO) {
        Floor floor = findById(id);

        floor.setFloorNumber(floorDTO.getFloorNumber());
        floor.setFloorPlan(floorDTO.getFloorPlan());

        if (floorDTO.getHouseId() != null) {
            House house = houseRepository.findById(floorDTO.getHouseId()).orElseThrow(
                    () -> new IllegalArgumentException(String.format(
                            "House id: %s in method editFloor NOT FOUND", floorDTO.getHouseId()
                    ))
            );
            floor.setHouse(house);
        }
        floorRepository.save(floor);
    }

    @Transactional
    public void deleteFloorById(Long id) {
        floorRepository.delete(findById(id));
    }
}
