package cz.mendelu.EAprojek.domain.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class Statistics {

    int countByCombinedMpgGreaterThan30 = 0;

    List<String> namesOfCarsWithTailpipeCo2InGramsMileFt1IsLessThan250 = new ArrayList<>();

    List<String> nameOf3CarsWithLowestAnnualFuelCostFt1 = new ArrayList<>();

    Map<String, Double> averageMpgByManufacturer= new HashMap<>();

    Map<String, String> mostCommonVehicleClassByManufacturer= new HashMap<>();

    int countFuelData = 0;

}
