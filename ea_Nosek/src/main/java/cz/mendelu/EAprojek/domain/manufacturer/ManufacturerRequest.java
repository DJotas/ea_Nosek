package cz.mendelu.EAprojek.domain.manufacturer;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ManufacturerRequest {

    @NotEmpty
    @Schema(description = "Full name of the manufacturer.", example = "Toyota")
    String name;

    @NotEmpty
    @Schema(description = "Country of origin", example = "Japan")
    String countryOfOrigin;

    @NotNull
    @Schema(description = "Year of establishment", example = "2000")
    Integer yearEstablished;

    public void toManufacturer(Manufacturer manufacturer) {
        manufacturer.setName(name);
        manufacturer.setCountryOfOrigin(countryOfOrigin);
        manufacturer.setYearEstablished(yearEstablished);
    }
}
