package kattsyn.dev.ApartmentsUnderConstruction.mappers;

import kattsyn.dev.ApartmentsUnderConstruction.dtos.ApartmentDTO;
import kattsyn.dev.ApartmentsUnderConstruction.entities.Apartment;
import kattsyn.dev.ApartmentsUnderConstruction.entities.Floor;
import kattsyn.dev.ApartmentsUnderConstruction.entities.Region;
import kattsyn.dev.ApartmentsUnderConstruction.service.FloorService;
import kattsyn.dev.ApartmentsUnderConstruction.service.RegionService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {RegionService.class, FloorService.class})
public interface ApartmentMapper {

    Apartment fromApartmentDTO(ApartmentDTO apartmentDTO);

    ApartmentDTO toApartmentDTO(Apartment apartment);

    default Region getRegionById(Integer regionId, @Context RegionService regionService) {
        return regionService.findById(regionId);
    }

    default Floor getFloorById(Long floorId, @Context FloorService floorService) {
        return floorService.findById(floorId);
    }
}
