package kattsyn.dev.ApartmentsUnderConstruction.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kattsyn.dev.ApartmentsUnderConstruction.service.StatsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("/stats")
public class StatsController {

    private final StatsService statsService;
    private final ObjectMapper objectMapper;

    @GetMapping("/apartments")
    public String getApartmentStatistics(Model model) throws JsonProcessingException {
        Map<String, Long> apartmentStatistics = statsService.getApartmentCountByStatus();
        String statisticsJson = objectMapper.writeValueAsString(apartmentStatistics);
        model.addAttribute("statistics", statisticsJson);
        System.out.println(apartmentStatistics);
        return "stats/apartments";
    }

    @GetMapping("/houses")
    public String getBuildingStatistics(Model model) {
        Map<String, Map<Integer, Long>> buildingStatistics = statsService.getApartmentCountByBuildingAndFloor();
        model.addAttribute("housesStatistics", buildingStatistics);
        return "stats/houses";
    }

}
