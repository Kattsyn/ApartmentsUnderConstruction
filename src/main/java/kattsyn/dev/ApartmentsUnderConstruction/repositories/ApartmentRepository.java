package kattsyn.dev.ApartmentsUnderConstruction.repositories;


import kattsyn.dev.ApartmentsUnderConstruction.entities.Apartment;
import kattsyn.dev.ApartmentsUnderConstruction.entities.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApartmentRepository extends JpaRepository<Apartment, Long>, JpaSpecificationExecutor<Apartment> {

    List<Apartment> findByFloor(Floor floor);


    @Query("SELECT DISTINCT a.amountOfRooms FROM Apartment a")
    List<Byte> findDistinctRooms();



}
