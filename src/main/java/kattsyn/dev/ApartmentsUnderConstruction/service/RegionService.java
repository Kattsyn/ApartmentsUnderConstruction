package kattsyn.dev.ApartmentsUnderConstruction.service;

import kattsyn.dev.ApartmentsUnderConstruction.repositories.RegionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RegionService {

    private final RegionRepository regionRepository;

    public List<String> getDistinctRegionsNames() {
        return regionRepository.getDistinctRegionsNames();
    }

}
