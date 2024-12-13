package kattsyn.dev.ApartmentsUnderConstruction.specifications;

import jakarta.persistence.criteria.*;
import kattsyn.dev.ApartmentsUnderConstruction.dtos.filters.HouseFilter;
import kattsyn.dev.ApartmentsUnderConstruction.entities.House;
import kattsyn.dev.ApartmentsUnderConstruction.entities.Region;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class HouseSpecification implements Specification<House> {

    private final HouseFilter filter;

    @Override
    public Predicate toPredicate(Root<House> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction();

        Join<House, Region> regionJoin = root.join("region", JoinType.INNER);

        // Фильтрация по региону
        if (filter.getRegionId() != null && filter.getRegionId() != 0) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(regionJoin.get("regionId"), filter.getRegionId()));
        }

        // Фильтрация по готовности к сдаче
        if (filter.getIsReady() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("isReady"), filter.getIsReady()));
        }

        return predicate;
    }
}
