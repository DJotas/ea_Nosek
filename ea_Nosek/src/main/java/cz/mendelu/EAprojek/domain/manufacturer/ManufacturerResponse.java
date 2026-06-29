package cz.mendelu.EAprojek.domain.manufacturer;

import cz.mendelu.EAprojek.domain.fuelData.FuelData;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


@Data
@AllArgsConstructor
public class ManufacturerResponse {


    public ManufacturerResponse(Manufacturer manufacturer) {
        this.id = manufacturer.getId();
        this.name = manufacturer.getName();
        this.countryOfOrigin = manufacturer.getCountryOfOrigin();
        this.yearEstablished = manufacturer.getYearEstablished();

        this.averCo2Emission = manufacturer.getAverCo2Emission();
        this.averFuelCost = manufacturer.getAverFuelCost();
        this.averYearCo2Emission = manufacturer.getAverYearCo2Emission();
        this.AverEngineIndex = manufacturer.getAverEngineIndex();
        this.AverCombinedMpg = manufacturer.getAverCombinedMpg();

        this.numberOfFuelData = manufacturer.getFuelData().size();

    }

    @NotNull
    Long id;

    @NotEmpty
    String name;

    @NotEmpty
    String countryOfOrigin;

    @NotNull
    Integer yearEstablished;

    BigDecimal averCo2Emission;

    BigDecimal averFuelCost;

    BigDecimal averYearCo2Emission;

    Integer numberOfFuelData;

    Integer AverCombinedMpg;

    Integer AverEngineIndex;


}
