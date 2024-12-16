package kattsyn.dev.ApartmentsUnderConstruction.mappers;

import kattsyn.dev.ApartmentsUnderConstruction.dtos.ApartmentDTO;
import kattsyn.dev.ApartmentsUnderConstruction.entities.Apartment;
import kattsyn.dev.ApartmentsUnderConstruction.entities.Floor;
import kattsyn.dev.ApartmentsUnderConstruction.entities.SaleStatus;
import kattsyn.dev.ApartmentsUnderConstruction.service.FloorService;
import kattsyn.dev.ApartmentsUnderConstruction.service.SaleStatusService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {SaleStatusService.class, FloorService.class})
public interface ApartmentMapper {

    @Mapping(target = "floor", source = "floorId")
    @Mapping(target = "status", source = "statusId")
    Apartment fromApartmentDTO(ApartmentDTO apartmentDTO);

    @Mapping(target = "floorId", source = "floor")
    @Mapping(target = "statusId", source = "status")
    ApartmentDTO toApartmentDTO(Apartment apartment);

    default SaleStatus getStatusById(Integer statusId, @Context SaleStatusService saleStatusService) {
        return saleStatusService.findById(statusId);
    }

    default Floor getFloorById(Long floorId, @Context FloorService floorService) {
        return floorService.findById(floorId);
    }

    default Integer getStatusIdByStatus(SaleStatus status) {
        return status.getSaleStatusId();
    }

    default Long getFloorIdByFloor(Floor floor) {
        return floor.getFloorId();
    }
}
