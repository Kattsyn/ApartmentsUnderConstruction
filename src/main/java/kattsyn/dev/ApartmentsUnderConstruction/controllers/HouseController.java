package kattsyn.dev.ApartmentsUnderConstruction.controllers;

import kattsyn.dev.ApartmentsUnderConstruction.service.HouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/houses")
@Slf4j
public class HouseController {

    private final HouseService houseService;

    @GetMapping("/test")
    public ResponseEntity<?> createTestRecord() {
        log.info("Controller: createTestRecord()..");
        return ResponseEntity.ok(houseService.addTestHouse(Optional.empty()));
    }
}
