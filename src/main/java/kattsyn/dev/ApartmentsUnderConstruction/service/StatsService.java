package kattsyn.dev.ApartmentsUnderConstruction.service;

import kattsyn.dev.ApartmentsUnderConstruction.repositories.ApartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StatsService {

    private final ApartmentRepository apartmentRepository;

    public Map<String, Long> getApartmentCountByStatus() {
        List<Object[]> result = apartmentRepository.findApartmentCountByStatus();
        return result.stream()
                .collect(Collectors.toMap(
                        row -> (String) row[0],
                        row -> (Long) row[1]
                ));
    }

    public Map<String, Map<Integer, Long>> getApartmentCountByBuildingAndFloor() {
        List<Object[]> result = apartmentRepository.countApartmentsByFloorAndBuilding();
        Map<String, Map<Integer, Long>> statistics = new HashMap<>();
        for (Object[] row : result) {
            String buildingName = (String) row[0];
            Integer floorNumber = (Integer) row[1];
            Long count = (Long) row[2];

            statistics.computeIfAbsent(buildingName, k -> new HashMap<>())
                    .put(floorNumber, count);
        }
        return statistics;
    }

}
