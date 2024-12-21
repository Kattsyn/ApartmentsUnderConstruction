package kattsyn.dev.ApartmentsUnderConstruction.service;

import kattsyn.dev.ApartmentsUnderConstruction.dtos.ApartmentReservationRequestDTO;
import kattsyn.dev.ApartmentsUnderConstruction.entities.Apartment;
import kattsyn.dev.ApartmentsUnderConstruction.entities.ApartmentReservation;
import kattsyn.dev.ApartmentsUnderConstruction.mappers.ApartmentReservationMapper;
import kattsyn.dev.ApartmentsUnderConstruction.repositories.ApartmentReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApartmentReservationService {

    private final ApartmentService apartmentService;
    private final ApartmentReservationRepository reservationRepository;
    private final ApartmentReservationMapper reservationMapper;
    private final SaleStatusService saleStatusService;

    public List<ApartmentReservation> findAll() {
        return reservationRepository.findAll();
    }

    public ApartmentReservation findById(Long reservationId) {
        return reservationRepository.findById(reservationId).orElseThrow(
                () -> new IllegalArgumentException(String.format("ApartmentReservation id: %s NOT FOUND", reservationId))
        );
    }

    public void save(ApartmentReservationRequestDTO apartmentReservationRequestDTO) {
        reservationRepository.save(reservationMapper.fromApartmentReservationRequestDTO(apartmentReservationRequestDTO));
    }

    public void save(ApartmentReservation apartmentReservation) {
        reservationRepository.save(apartmentReservation);
    }

    public void confirmReservation(Long reservationId) {
        ApartmentReservation apartmentReservation = findById(reservationId);
        Apartment apartment = apartmentReservation.getApartment();
        apartment.setStatus(saleStatusService.getReservedStatus());
        apartmentService.save(apartment);
        apartmentReservation.setIsConfirmed(true);
        save(apartmentReservation);
    }

    public void deleteReservation(Long reservationId) {
        ApartmentReservation apartmentReservation = findById(reservationId);

        reservationRepository.delete(apartmentReservation);
    }

}
