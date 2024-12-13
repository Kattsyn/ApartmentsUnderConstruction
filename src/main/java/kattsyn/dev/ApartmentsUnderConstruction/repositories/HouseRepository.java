package kattsyn.dev.ApartmentsUnderConstruction.repositories;

import kattsyn.dev.ApartmentsUnderConstruction.entities.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HouseRepository extends JpaRepository<House, Long>, JpaSpecificationExecutor<House> {


    @Query("SELECT DISTINCT h.name from House h")
    List<String> getDistinctNames();
}
