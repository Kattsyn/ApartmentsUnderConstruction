package kattsyn.dev.ApartmentsUnderConstruction.service;

import kattsyn.dev.ApartmentsUnderConstruction.dtos.ApartmentDTO;
import kattsyn.dev.ApartmentsUnderConstruction.dtos.filters.ApartmentFilter;
import kattsyn.dev.ApartmentsUnderConstruction.entities.Apartment;
import kattsyn.dev.ApartmentsUnderConstruction.mappers.ApartmentMapper;
import kattsyn.dev.ApartmentsUnderConstruction.repositories.ApartmentRepository;
import kattsyn.dev.ApartmentsUnderConstruction.specifications.ApartmentSpecification;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;
    private final ApartmentMapper apartmentMapper;

    public List<Byte> findDistinctAmountOfRooms() {
        return apartmentRepository.findDistinctRooms();
    }

    public Apartment findById(Long id) {
        return apartmentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Apartment id: %s NOT FOUND", id))
        );
    }

    public Page<Apartment> getFilteredApartmentPage(ApartmentFilter filter, int pageNumber, int pageSize) {
        return apartmentRepository.findAll(
                new ApartmentSpecification(filter),
                PageRequest.of(pageNumber, pageSize));
    }

    public String showInfoPage(Model model, Long id) {
        Optional<Apartment> apartment = apartmentRepository.findById(id);
        if (apartment.isEmpty()) {
            log.error("Apartment with id {} not found", id);
            return "redirect:/apartments";
        }
        model.addAttribute("apartment", apartment.get());
        return "apartments/info-apartment";
    }


    public void save(ApartmentDTO apartmentDTO) {
        Apartment apartment = apartmentMapper.fromApartmentDTO(apartmentDTO);
        apartmentRepository.save(apartment);
    }

    public void deleteApartmentById(Long id) {
        apartmentRepository.delete(findById(id));
    }

}
