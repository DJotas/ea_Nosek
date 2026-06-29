package cz.mendelu.EAprojek.domain.fuelData;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class FuelDataResponse {

    @NotNull
    private Long id;

    @NotNull
    private Integer year;

    @NotEmpty
    private String make;

    @NotEmpty
    private String model;

    @NotEmpty
    private String vehicleClass;

    private String drive;

    private String transmission;

    private Integer engineIndex;

    private String engineDescriptor;

    private BigDecimal engineCylinders;

    private BigDecimal engineDisplacement;

    private String turbocharger;

    private String fuelType;

    private Integer cityMpgFt1;

    private Integer highwayMpgFt1;

    private BigDecimal unroundedCityMpgFt1;

    private BigDecimal unroundedHighwayMpgFt1;

    private Integer combinedMpg;

    private BigDecimal annualFuelCostFt1;

    private BigDecimal annualConsumptionInBarrelsFt1;

    private BigDecimal tailpipeCo2InGramsMileFt1;

    public FuelDataResponse(FuelData fuelData) {
        this.id = fuelData.getId();
        this.year = fuelData.getYear();
        this.make = fuelData.getMake();
        this.model = fuelData.getModel();
        this.vehicleClass = fuelData.getVehicleClass();
        this.drive = fuelData.getDrive();
        this.transmission = fuelData.getTransmission();
        this.engineIndex = fuelData.getEngineIndex();
        this.engineDescriptor = fuelData.getEngineDescriptor();
        this.engineCylinders = fuelData.getEngineCylinders();
        this.engineDisplacement = fuelData.getEngineDisplacement();
        this.turbocharger = fuelData.getTurbocharger();
        this.fuelType = fuelData.getFuelType();
        this.cityMpgFt1 = fuelData.getCityMpgFt1();
        this.highwayMpgFt1 = fuelData.getHighwayMpgFt1();
        this.unroundedCityMpgFt1 = fuelData.getUnroundedCityMpgFt1();
        this.unroundedHighwayMpgFt1 = fuelData.getUnroundedHighwayMpgFt1();
        this.combinedMpg = fuelData.getCombinedMpg();
        this.annualFuelCostFt1 = fuelData.getAnnualFuelCostFt1();
        this.annualConsumptionInBarrelsFt1 = fuelData.getAnnualConsumptionInBarrelsFt1();
        this.tailpipeCo2InGramsMileFt1 = fuelData.getTailpipeCo2InGramsMileFt1();
    }
}
