package kattsyn.dev.ApartmentsUnderConstruction.repositories;

import kattsyn.dev.ApartmentsUnderConstruction.entities.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FloorRepository extends JpaRepository<Floor, Long> {

    @Query("SELECT DISTINCT f.floorNumber FROM Floor f")
    List<Byte> distinctFloorNumbers();

}
