package cz.mendelu.EAprojek.domain.manufacturer;

import cz.mendelu.EAprojek.domain.fuelData.FuelData;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "manufacturer")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotEmpty
    private String name;

    private String countryOfOrigin;

    @NotNull
    private Integer yearEstablished;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "manufacturer", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    Set<FuelData> fuelData = new HashSet<>();

    public Manufacturer(String name, String countryOfOrigin, Integer yearEstablished) {
        this.name = name;
        this.countryOfOrigin = countryOfOrigin;
        this.yearEstablished = yearEstablished;
    }

    public void addFuelData(FuelData fuelData) {
        this.fuelData.add(fuelData);
    }

    public BigDecimal getAverCo2Emission() {
        BigDecimal sum = BigDecimal.ZERO;

        if (fuelData.isEmpty()) {
            return BigDecimal.ZERO;
        }

        for (FuelData data : fuelData) {
            sum = sum.add(data.getTailpipeCo2InGramsMileFt1());
        }

        return sum.divide(BigDecimal.valueOf(fuelData.size()), BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getAverFuelCost() {
        BigDecimal sum = BigDecimal.ZERO;

        if (fuelData.isEmpty()) {
            return BigDecimal.ZERO;
        }

        for (FuelData fuelData : fuelData) {
            sum = sum.add(fuelData.getAnnualFuelCostFt1());
        }
        return sum.divide(BigDecimal.valueOf(fuelData.size()), BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getAverYearCo2Emission() {
        BigDecimal sum = BigDecimal.ZERO;
        if (fuelData.isEmpty()) {
            return BigDecimal.ZERO;
        }
        for (FuelData data : fuelData) {
            sum = sum.add(data.getTailpipeCo2InGramsMileFt1().multiply(BigDecimal.valueOf(14263)));
        }
        return sum.divide(BigDecimal.valueOf(fuelData.size()), BigDecimal.ROUND_HALF_UP);
    }

    public Integer getAverCombinedMpg(){
        Integer sum = 0 ;
        if (fuelData.isEmpty()) {
            return 0;
        }

        for (FuelData fuelData : fuelData) {
           sum = sum + fuelData.getCombinedMpg();
        }
        return sum/fuelData.size();
    }

    public Integer getAverEngineIndex() {
        Integer sum = 0;
        if (fuelData.isEmpty()) {
            return 0;
        }

        for (FuelData fuelData : fuelData) {
            sum = sum + fuelData.getEngineIndex();
        }
        return sum / fuelData.size();

    }






}
