package kattsyn.dev.ApartmentsUnderConstruction.service;

import kattsyn.dev.ApartmentsUnderConstruction.entities.Region;
import kattsyn.dev.ApartmentsUnderConstruction.repositories.RegionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RegionService {

    private final RegionRepository regionRepository;


    public Region findById(Integer id) {
        return regionRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException(String.format("Region id: %s NOT FOUND", id)));
    }

    public List<Region> findAll() {
        return regionRepository.findAll();
    }
}
