package cz.mendelu.EAprojek.domain.fuelData;

import org.springframework.data.jpa.domain.Specification;

public class FuelDataSpecification{
    public static Specification<FuelData> byFilter(FuelDataFilter filter) {
        return (root, query, criteriaBuilder) -> {
            var predicates = criteriaBuilder.conjunction();

            if (filter.getMake() != null && !filter.getMake().isBlank()) {
                predicates = criteriaBuilder.and(predicates, criteriaBuilder.like(criteriaBuilder.lower(root.get("make")), "%" + filter.getMake().toLowerCase() + "%"));
            }
            if (filter.getModel() != null && !filter.getModel().isBlank()) {
                predicates = criteriaBuilder.and(predicates, criteriaBuilder.like(criteriaBuilder.lower(root.get("model")), "%" + filter.getModel().toLowerCase() + "%"));
            }
            if (filter.getFuelType() != null && !filter.getFuelType().isBlank()) {
                predicates = criteriaBuilder.and(predicates, criteriaBuilder.equal(root.get("fuelType"), filter.getFuelType()));
            }
            if (filter.getVehicleClass() != null && !filter.getVehicleClass().isBlank()) {
                predicates = criteriaBuilder.and(predicates, criteriaBuilder.equal(root.get("vehicleClass"), filter.getVehicleClass()));
            }
            if (filter.getYearFrom() != null) {
                predicates = criteriaBuilder.and(predicates, criteriaBuilder.greaterThanOrEqualTo(root.get("year"), filter.getYearFrom()));
            }
            if (filter.getYearTo() != null) {
                predicates = criteriaBuilder.and(predicates, criteriaBuilder.lessThanOrEqualTo(root.get("year"), filter.getYearTo()));
            }
            if (filter.getMinCombinedMpg() != null) {
                predicates = criteriaBuilder.and(predicates, criteriaBuilder.greaterThanOrEqualTo(root.get("combinedMpg"), filter.getMinCombinedMpg()));
            }
            if (filter.getMaxCombinedMpg() != null) {
                predicates = criteriaBuilder.and(predicates, criteriaBuilder.lessThanOrEqualTo(root.get("combinedMpg"), filter.getMaxCombinedMpg()));
            }

            return predicates;
        };
    }
        



}