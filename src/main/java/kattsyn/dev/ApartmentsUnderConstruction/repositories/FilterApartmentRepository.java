package kattsyn.dev.ApartmentsUnderConstruction.repositories;

import kattsyn.dev.ApartmentsUnderConstruction.dtos.filters.ApartmentFilter;
import kattsyn.dev.ApartmentsUnderConstruction.entities.Apartment;

import java.util.List;

public interface FilterApartmentRepository {
    List<Apartment> findAllByFilter(ApartmentFilter filter);

}
