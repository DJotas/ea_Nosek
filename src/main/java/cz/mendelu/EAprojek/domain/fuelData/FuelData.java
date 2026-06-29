package cz.mendelu.EAprojek.domain.fuelData;

import cz.mendelu.EAprojek.domain.manufacturer.Manufacturer;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@Table(name = "fuelData")
public class FuelData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer year;

    @NotEmpty
    private String make;

    @NotNull
    @ManyToOne
    private Manufacturer manufacturer;

    @NotEmpty
    private String model;

    @NotEmpty
    @Column(name = "class")
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


    @Column(name = "tailpipe_co2_in_grams_mile_ft1")
    private BigDecimal tailpipeCo2InGramsMileFt1;

    public FuelData(@NotNull Integer year, @NotEmpty String make, @NotEmpty String model, @NotEmpty String vehicleClass,
                    String drive, String transmission, Integer engineIndex, String engineDescriptor,
                    BigDecimal engineCylinders, BigDecimal engineDisplacement, String turbocharger, String fuelType,
                    Integer cityMpgFt1, Integer highwayMpgFt1, BigDecimal unroundedCityMpgFt1,
                    BigDecimal unroundedHighwayMpgFt1, Integer combinedMpg, BigDecimal annualFuelCostFt1,
                    BigDecimal annualConsumptionInBarrelsFt1, BigDecimal tailpipeCo2InGramsMileFt1) {
        this.year = year;
        this.make = make;
        this.model = model;
        this.vehicleClass = vehicleClass;
        this.drive = drive;
        this.transmission = transmission;
        this.engineIndex = engineIndex;
        this.engineDescriptor = engineDescriptor;
        this.engineCylinders = engineCylinders;
        this.engineDisplacement = engineDisplacement;
        this.turbocharger = turbocharger;
        this.fuelType = fuelType;
        this.cityMpgFt1 = cityMpgFt1;
        this.highwayMpgFt1 = highwayMpgFt1;
        this.unroundedCityMpgFt1 = unroundedCityMpgFt1;
        this.unroundedHighwayMpgFt1 = unroundedHighwayMpgFt1;
        this.combinedMpg = combinedMpg;
        this.annualFuelCostFt1 = annualFuelCostFt1;
        this.annualConsumptionInBarrelsFt1 = annualConsumptionInBarrelsFt1;
        this.tailpipeCo2InGramsMileFt1 = tailpipeCo2InGramsMileFt1;
    }

}
