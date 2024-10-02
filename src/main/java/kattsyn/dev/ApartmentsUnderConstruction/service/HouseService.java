package kattsyn.dev.ApartmentsUnderConstruction.service;

import kattsyn.dev.ApartmentsUnderConstruction.entities.House;
import kattsyn.dev.ApartmentsUnderConstruction.repositories.HouseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class HouseService {

    private final HouseRepository houseRepository;

    public House addTestHouse(Optional<House> house) {
        if (house.isPresent()) {
            log.info("Saving prepared house...");
            return houseRepository.save(house.get());
        } else {
            log.info("Saving new test House...");
            return houseRepository.save(new House(
                    "some address",
                    "some name",
                    LocalDate.now(),
                    LocalDate.now(),
                    LocalDate.now()
            ));
        }
    }


}
