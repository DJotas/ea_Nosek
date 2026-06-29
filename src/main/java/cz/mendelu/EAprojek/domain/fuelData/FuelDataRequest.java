package cz.mendelu.EAprojek.domain.fuelData;

import cz.mendelu.EAprojek.domain.manufacturer.ManufacturerService;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuelDataRequest {

    @NotNull
    @Schema(description = "Year of the vehicle.", example = "2022")
    private Integer year;

    @NotEmpty
    @Schema(description = "Make of the vehicle.", example = "Toyota")
    private String make;

    @NotEmpty
    @Schema(description = "Model of the vehicle.", example = "Corolla")
    private String model;

    @NotEmpty
    @Schema(description = "Class of the vehicle.", example = "Sedan")
    private String vehicleClass;

    @NotEmpty
    @Schema(description = "Drive type of the vehicle.", example = "FWD")
    private String drive;

    @NotEmpty
    @Schema(description = "Transmission of the vehicle.", example = "Automatic")
    private String transmission;

    @NotEmpty
    @Schema(description = "Engine index of the vehicle.", example = "1")
    private Integer engineIndex;

    @NotEmpty
    @Schema(description = "Engine descriptor of the vehicle.", example = "V6")
    private String engineDescriptor;

    @NotNull
    @Schema(description = "Number of engine cylinders.", example = "6.0")
    private BigDecimal engineCylinders;

    @NotNull
    @Schema(description = "Engine displacement in liters.", example = "3.5")
    private BigDecimal engineDisplacement;

    @NotEmpty
    @Schema(description = "Turbocharger type.", example = "Yes")
    private String turbocharger;

    @NotEmpty
    @Schema(description = "Primary fuel type.", example = "Gasoline")
    private String fuelType;

    @Schema(description = "City MPG for fuel type 1.", example = "30")
    @NotNull
    @Min(0)
    private Integer cityMpgFt1;

    @Schema(description = "Highway MPG for fuel type 1.", example = "40")
    @NotNull
    @Min(0)
    private Integer highwayMpgFt1;

    @Schema(description = "Unrounded city MPG for fuel type 1.", example = "30.5")
    @NotNull
    @Min(0)
    private BigDecimal unroundedCityMpgFt1;

    @Schema(description = "Unrounded highway MPG for fuel type 1.", example = "40.5")
    @NotNull
    @Min(0)
    private BigDecimal unroundedHighwayMpgFt1;

    @Schema(description = "Combined MPG.", example = "35")
    @NotNull
    @Min(0)
    private Integer combinedMpg;

    @Schema(description = "Annual fuel cost for fuel type 1.", example = "1200.00")
    @NotNull
    @Min(0)
    private BigDecimal annualFuelCostFt1;

    @Schema(description = "Annual fuel consumption in barrels for fuel type 1.", example = "15.0")
    @NotNull
    @Min(0)
    private BigDecimal annualConsumptionInBarrelsFt1;

    @Schema(description = "Tailpipe CO2 emissions in grams per mile for fuel type 1.", example = "300.0")
    @NotNull
    @Min(0)
    private BigDecimal tailpipeCo2InGramsMileFt1;

    public void toFuelData(FuelData fuelData) {
        fuelData.setYear(year);
        fuelData.setMake(make);
        fuelData.setModel(model);
        fuelData.setVehicleClass(vehicleClass);
        fuelData.setDrive(drive);
        fuelData.setTransmission(transmission);
        fuelData.setEngineIndex(engineIndex);
        fuelData.setEngineDescriptor(engineDescriptor);
        fuelData.setEngineCylinders(engineCylinders);
        fuelData.setEngineDisplacement(engineDisplacement);
        fuelData.setTurbocharger(turbocharger);
        fuelData.setFuelType(fuelType);
        fuelData.setCityMpgFt1(cityMpgFt1);
        fuelData.setHighwayMpgFt1(highwayMpgFt1);
        fuelData.setUnroundedCityMpgFt1(unroundedCityMpgFt1);
        fuelData.setUnroundedHighwayMpgFt1(unroundedHighwayMpgFt1);
        fuelData.setCombinedMpg(combinedMpg);
        fuelData.setAnnualFuelCostFt1(annualFuelCostFt1);
        fuelData.setAnnualConsumptionInBarrelsFt1(annualConsumptionInBarrelsFt1);
        fuelData.setTailpipeCo2InGramsMileFt1(tailpipeCo2InGramsMileFt1);
    }
}
