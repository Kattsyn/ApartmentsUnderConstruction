package kattsyn.dev.ApartmentsUnderConstruction.service;

import kattsyn.dev.ApartmentsUnderConstruction.entities.SaleStatus;
import kattsyn.dev.ApartmentsUnderConstruction.repositories.SaleStatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SaleStatusService {

    SaleStatusRepository statusRepository;

    public List<SaleStatus> findAll() {
        return statusRepository.findAll();
    }

}
