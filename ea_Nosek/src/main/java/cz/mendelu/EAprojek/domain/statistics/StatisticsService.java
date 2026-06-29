package cz.mendelu.EAprojek.domain.statistics;

import cz.mendelu.EAprojek.domain.fuelData.FuelData;
import cz.mendelu.EAprojek.domain.fuelData.FuelDataRepository;
import cz.mendelu.EAprojek.domain.manufacturer.ManufacturerRepository;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticsService {


    final private FuelDataRepository fuelDataRepository;

    final private ManufacturerRepository manufacturerRepository;


    public StatisticsService(FuelDataRepository fuelDataRepository,ManufacturerRepository manufacturerRepository) {
        this.fuelDataRepository = fuelDataRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    public Map<String, String> getMostCommonVehicleClassByManufacturer() {
        List<Object[]> results = fuelDataRepository.findMostCommonVehicleClass();
        Map<String, String> mostCommonVehicleClasses = new HashMap<>();

        String currentManufacturer = null;
        for (Object[] result : results) {
            String manufacturer = (String) result[0];
            String vehicleClass = (String) result[1];


            if (!manufacturer.equals(currentManufacturer)) {
                mostCommonVehicleClasses.put(manufacturer, vehicleClass);
                currentManufacturer = manufacturer;
            }
        }
        return mostCommonVehicleClasses;
    }

    public Map<String, Double> getAverageMpgByManufacturer() {
        List<Object[]> results = fuelDataRepository.findAverageMpgByManufacturer();
        Map<String, Double> averageMpgByManufacturer = new HashMap<>();

        for (Object[] result : results) {
            String manufacturer = (String) result[0];
            Double averageMpg = (Double) result[1];
            averageMpgByManufacturer.put(manufacturer, averageMpg);
        }
        return averageMpgByManufacturer;
    }

    public List<String> getNamesOfModels(Iterable<FuelData> fuelData) {
        List<String> results = new ArrayList<>();
        fuelData.forEach(u -> results.add(u.getModel()));
        return results;
    }

    public Statistics getStatistics() {
        return new Statistics(
                fuelDataRepository.countByCombinedMpgGreaterThan(30),
                getNamesOfModels(fuelDataRepository.findByTailpipeCo2InGramsMileFt1IsLessThan(BigDecimal.valueOf(250))),
                getNamesOfModels(fuelDataRepository.findTop3ByOrderByAnnualFuelCostFt1Asc()),
                getAverageMpgByManufacturer(),
                getMostCommonVehicleClassByManufacturer(),
                fuelDataRepository.countFuelData()
        );
    }
}
