package kattsyn.dev.ApartmentsUnderConstruction.repositories;


import kattsyn.dev.ApartmentsUnderConstruction.entities.Apartment;
import kattsyn.dev.ApartmentsUnderConstruction.entities.Floor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApartmentRepository extends JpaRepository<Apartment, Long>, JpaSpecificationExecutor<Apartment> {

    List<Apartment> findByFloor(Floor floor);

    Page<Apartment> findAll(Specification<Apartment> spec, Pageable pageable);

    @Query("SELECT DISTINCT a.amountOfRooms FROM Apartment a")
    List<Byte> findDistinctRooms();



}
