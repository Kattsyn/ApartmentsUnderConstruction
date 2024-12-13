package kattsyn.dev.ApartmentsUnderConstruction.mappers;

import kattsyn.dev.ApartmentsUnderConstruction.dtos.HouseDTO;
import kattsyn.dev.ApartmentsUnderConstruction.entities.House;
import kattsyn.dev.ApartmentsUnderConstruction.entities.Region;
import kattsyn.dev.ApartmentsUnderConstruction.service.RegionService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {RegionService.class})
public interface HouseMapper {

    @Mapping(target = "region", source = "regionId")
    House fromHouseDTO(HouseDTO houseDTO);

    HouseDTO toHouseDTO(House house);

    default Region getRegionById(Integer regionId, @Context RegionService regionService) {
        return regionService.findById(regionId);
    }
}
