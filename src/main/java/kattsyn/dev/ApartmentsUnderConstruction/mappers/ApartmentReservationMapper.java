package kattsyn.dev.ApartmentsUnderConstruction.mappers;

import kattsyn.dev.ApartmentsUnderConstruction.dtos.ApartmentReservationRequestDTO;
import kattsyn.dev.ApartmentsUnderConstruction.entities.Apartment;
import kattsyn.dev.ApartmentsUnderConstruction.entities.ApartmentReservation;
import kattsyn.dev.ApartmentsUnderConstruction.service.ApartmentService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {ApartmentService.class})
public interface ApartmentReservationMapper {

    @Mapping(target = "apartment", source = "apartmentId")
    ApartmentReservation fromApartmentReservationRequestDTO(ApartmentReservationRequestDTO apartmentReservationRequestDTO);

    default Apartment getApartmentById(Long apartmentId, @Context ApartmentService apartmentService) {
        return apartmentService.findById(apartmentId);
    }

}
