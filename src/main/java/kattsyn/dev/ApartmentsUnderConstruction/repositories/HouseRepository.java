package kattsyn.dev.ApartmentsUnderConstruction.repositories;

import kattsyn.dev.ApartmentsUnderConstruction.entities.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HouseRepository extends JpaRepository<House, Long>, JpaSpecificationExecutor<House> {

    @Query("SELECT MIN(a.apartmentCost) FROM Apartment a where a.floor.house.houseId = :id")
    Integer getMinApartmentPriceByHouseId(@Param("id") Long id);

    @Query("SELECT DISTINCT h.name from House h")
    List<String> getDistinctNames();
}
