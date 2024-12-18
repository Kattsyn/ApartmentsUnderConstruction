package kattsyn.dev.ApartmentsUnderConstruction.repositories;

import kattsyn.dev.ApartmentsUnderConstruction.entities.ApartmentReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartmentReservationRepository extends JpaRepository<ApartmentReservation, Long> {
}
