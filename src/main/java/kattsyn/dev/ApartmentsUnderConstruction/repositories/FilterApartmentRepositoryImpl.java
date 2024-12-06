package kattsyn.dev.ApartmentsUnderConstruction.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import kattsyn.dev.ApartmentsUnderConstruction.dtos.filters.ApartmentFilter;
import kattsyn.dev.ApartmentsUnderConstruction.entities.Apartment;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class FilterApartmentRepositoryImpl implements FilterApartmentRepository{
    private final EntityManager entityManager;
    @Override
    public List<Apartment> findAllByFilter(ApartmentFilter filter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Apartment> criteriaQuery = criteriaBuilder.createQuery(Apartment.class);

        Root<Apartment> apartment = criteriaQuery.from(Apartment.class);
        criteriaQuery.select(apartment);

        List<Predicate> predicates = new ArrayList<>();

        //todo: Добавить предикаты

        if (filter.getAmountOfRooms() != 0) {
            predicates.add(criteriaBuilder.equal(apartment.get("amountOfRooms"), filter.getAmountOfRooms()));
        }
        if (filter.getMinApartmentCost() != 0) {
            predicates.add(criteriaBuilder.greaterThan(apartment.get("apartmentCost"), filter.getMinApartmentCost()));
        }
        if (filter.getMaxApartmentCost() != 0) {
            predicates.add(criteriaBuilder.lessThan(apartment.get("apartmentCost"), filter.getMaxApartmentCost()));
        }
        criteriaQuery.where(predicates.toArray(Predicate[]::new));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
