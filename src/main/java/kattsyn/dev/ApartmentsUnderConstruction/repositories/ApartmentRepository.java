package kattsyn.dev.ApartmentsUnderConstruction.repositories;


import kattsyn.dev.ApartmentsUnderConstruction.dtos.filters.ApartmentFilter;
import kattsyn.dev.ApartmentsUnderConstruction.entities.Apartment;
import kattsyn.dev.ApartmentsUnderConstruction.entities.Floor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApartmentRepository extends JpaRepository<Apartment, Long>, FilterApartmentRepository {

    List<Apartment> findByFloor(Floor floor);

}
