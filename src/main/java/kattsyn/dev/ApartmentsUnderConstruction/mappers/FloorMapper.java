package kattsyn.dev.ApartmentsUnderConstruction.mappers;

import kattsyn.dev.ApartmentsUnderConstruction.dtos.FloorDTO;
import kattsyn.dev.ApartmentsUnderConstruction.entities.Floor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FloorMapper {

    Floor fromFloorDTO(FloorDTO floorDTO);

    FloorDTO toFloorDTO(Floor floor);

}
