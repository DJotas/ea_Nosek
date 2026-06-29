package cz.mendelu.EAprojek.domain.fuelData;

import lombok.Data;


@Data
public class FuelDataFilter {

    private String make;
    private String model;
    private String fuelType;
    private String vehicleClass;

    private Integer yearFrom;
    private Integer yearTo;

    private Integer minCombinedMpg;
    private Integer maxCombinedMpg;
}
