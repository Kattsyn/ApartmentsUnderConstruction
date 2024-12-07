package kattsyn.dev.ApartmentsUnderConstruction.specifications;

import jakarta.persistence.criteria.*;
import kattsyn.dev.ApartmentsUnderConstruction.dtos.filters.ApartmentFilter;
import kattsyn.dev.ApartmentsUnderConstruction.entities.Apartment;
import kattsyn.dev.ApartmentsUnderConstruction.entities.Floor;
import kattsyn.dev.ApartmentsUnderConstruction.entities.House;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class ApartmentSpecification implements Specification<Apartment> {

    private final ApartmentFilter filter;

    @Override
    public Predicate toPredicate(Root<Apartment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        // Список условий фильтрации
        Predicate predicate = criteriaBuilder.conjunction(); // Это всегда True, чтобы добавить другие условия

        /*
        // Фильтрация по региону (House -> Apartment)
        if (filter.getRegion() != null && !filter.getRegion().isEmpty()) {
            Join<Apartment, House> houseJoin = root.join("house", JoinType.INNER);
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(houseJoin.get("region"), filter.getRegion()));
        }

         */
        Join<Apartment, Floor> floorJoin = root.join("floor", JoinType.INNER);
        Join<Floor, House> houseJoin = floorJoin.join("house", JoinType.INNER);

        // Фильтрация по количеству комнат
        if (filter.getAmountOfRooms() != 0) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("amountOfRooms"), filter.getAmountOfRooms()));
        }

        // Фильтрация по минимальной стоимости
        if (filter.getMinApartmentCost() != 0) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("apartmentCost"), filter.getMinApartmentCost()));
        }

        // Фильтрация по максимальной стоимости
        if (filter.getMaxApartmentCost() != 0) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("apartmentCost"), filter.getMaxApartmentCost()));
        }

        // Фильтрация по минимальной площади квартиры
        if (filter.getMinTotalArea() != 0) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("totalArea"), filter.getMinTotalArea()));
        }

        // Фильтрация по максимальной площади квартиры
        if (filter.getMaxTotalArea() != 0) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("totalArea"), filter.getMaxTotalArea()));
        }

        // Фильтрация по имени дома (объекта)
        if (filter.getHouseName() != null && !filter.getHouseName().isEmpty()) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(houseJoin.get("name"), filter.getHouseName()));
        }

        // Фильтрация по номеру этажа
        if (filter.getFloorNumber() != 0) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(floorJoin.get("floorNumber"), filter.getFloorNumber()));
        }

        return predicate;
    }
}
