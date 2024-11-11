package kattsyn.dev.ApartmentsUnderConstruction.repositories;

import kattsyn.dev.ApartmentsUnderConstruction.entities.SaleStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<SaleStatus, Long> {
}
