package kattsyn.dev.ApartmentsUnderConstruction.utils;

import com.vaadin.exampledata.DataType;
import com.vaadin.exampledata.ExampleDataGenerator;
import com.vaadin.flow.data.provider.DataGenerator;
import kattsyn.dev.ApartmentsUnderConstruction.entities.House;
import kattsyn.dev.ApartmentsUnderConstruction.repositories.HouseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Component
@Slf4j
public class MyDataGenerator {

    @Bean
    public CommandLineRunner createDemoDataForHouseTable(HouseRepository houseRepository) {
        return args -> {
            if (houseRepository.count() == 0) {
                log.info("Generating data in table houses...");
                ExampleDataGenerator<House> generator = new ExampleDataGenerator<>(House.class, LocalDateTime.now());
                generator.setData(House::setAddress, DataType.ADDRESS);
                generator.setData(House::setName, DataType.WORD);
                generator.setData(House::setBuildingStartDate, DataType.DATE_LAST_1_YEAR);
                generator.setData(House::setPlannedBuildingEndDate, DataType.DATE_NEXT_7_DAYS);
                generator.setData(House::setCommissioningDate, DataType.DATE_NEXT_1_YEAR);

                StopWatch stop = new StopWatch();
                stop.start();
                List<House> houses = generator.create(1000, new Random().nextInt());
                log.info("List<House> has " + houses.size() + " records.");
                houseRepository.saveAll(houses);
                stop.stop();
                log.info("Demo data generated in " + stop.getTotalTimeMillis() + "ms. ");
            }
        };
    }
}
