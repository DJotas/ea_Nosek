package cz.mendelu.EAprojek.utils.data;


import cz.mendelu.EAprojek.domain.fuelData.FuelDataRepository;
import cz.mendelu.EAprojek.utils.exceptions.NotFoundException;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import com.opencsv.CSVReader;
import cz.mendelu.EAprojek.domain.fuelData.FuelData;
import cz.mendelu.EAprojek.domain.manufacturer.Manufacturer;
import cz.mendelu.EAprojek.domain.manufacturer.ManufacturerRepository;
import cz.mendelu.EAprojek.domain.manufacturer.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DataImporter {

    private final FuelDataRepository fuelDataRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final ResourceLoader resourceLoader;

    @Autowired
    public DataImporter(FuelDataRepository fuelDataRepository, ResourceLoader resourceLoader, ManufacturerRepository manufacturerRepository) {
        this.fuelDataRepository = fuelDataRepository;
        this.resourceLoader = resourceLoader;
        this.manufacturerRepository = manufacturerRepository;
    }

    @PostConstruct
    @Transactional
    public void importData() {
        importManufacturers();
        importFuelData();
    }

    @Transactional
    public void importManufacturers() {
        Resource resource = resourceLoader.getResource("classpath:data/manufacturers.csv");
        List<Manufacturer> manufacturers = parseManufacturers(resource);
        manufacturerRepository.saveAll(manufacturers);
    }

    public List<Manufacturer> parseManufacturers(Resource resource) {
        List<Manufacturer> manufacturers = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(resource.getFile()))) {
            // skip the header row
            reader.readNext();

            // read row by row
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                String name = nextLine[0];
                String countryOfOrigin = nextLine[1];
                Integer yearEstablished = parseInteger(nextLine[2]);


                if (name.isEmpty() || countryOfOrigin.isEmpty() || yearEstablished == null) {
                    log.error("Missing required fields for manufacturer: {}", String.join(",", nextLine));
                    continue;
                }

                Manufacturer manufacturer = new Manufacturer();
                manufacturer.setName(name);
                manufacturer.setCountryOfOrigin(countryOfOrigin);
                manufacturer.setYearEstablished(yearEstablished);

                manufacturers.add(manufacturer);
            }
            log.info("Loaded {} manufacturers successfully.", manufacturers.size());
        } catch (Exception e) {
            log.error("Error parsing CSV file", e);
        }
        return manufacturers;
    }

    @Transactional
    public void importFuelData() {
        // get data from 'resources' folder
        Resource resource = resourceLoader.getResource("classpath:data/fuel.csv");

        // create FuelData from data
        List<FuelData> fuelDataList = parseFuelData(resource);

        // save to db
        fuelDataRepository.saveAll(fuelDataList);
    }

    public List<FuelData> parseFuelData(Resource resource) {
        List<FuelData> fuelDataList = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(resource.getFile()))) {
            // skip the header row
            reader.readNext();

            // read row by row
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                Integer year = Integer.parseInt(nextLine[0]);
                String make = nextLine[1];
                String model = nextLine[2];
                String vehicleClass = nextLine[3];
                String drive = nextLine[4];
                String transmission = nextLine[5];
                Integer engineIndex = parseInteger(nextLine[6]);
                String engineDescriptor = nextLine[7];
                BigDecimal engineCylinders = parseBigDecimal(nextLine[8]);
                BigDecimal engineDisplacement = parseBigDecimal(nextLine[9]);
                String turbocharger = nextLine[10];
                String fuelType = nextLine[11];
                Integer cityMpgFt1 = parseInteger(nextLine[12]);
                Integer highwayMpgFt1 = parseInteger(nextLine[13]);
                BigDecimal unroundedCityMpgFt1 = parseBigDecimal(nextLine[14]);
                BigDecimal unroundedHighwayMpgFt1 = parseBigDecimal(nextLine[15]);
                Integer combinedMpg = parseInteger(nextLine[16]);
                BigDecimal annualFuelCostFt1 = parseBigDecimal(nextLine[17]);
                BigDecimal annualConsumptionInBarrelsFt1 = parseBigDecimal(nextLine[18]);
                BigDecimal tailpipeCo2InGramsMileFt1 = parseBigDecimal(nextLine[19]);

                if (year == null || make.isEmpty() || model.isEmpty() || vehicleClass.isEmpty()) {
                    log.error("Missing required fields for fuel data: {}", String.join(",", nextLine));
                    continue;
                }

                Optional<Manufacturer> manufacturerOpt = manufacturerRepository.findByName(make);
                if (!manufacturerOpt.isPresent()) {
                    log.error("Manufacturer not found for make: {}", make);
                    continue;
                }

                Manufacturer manufacturer = manufacturerOpt.get();
                FuelData fuelData = new FuelData(year, make, model, vehicleClass, drive, transmission, engineIndex,
                        engineDescriptor, engineCylinders, engineDisplacement, turbocharger, fuelType, cityMpgFt1,
                        highwayMpgFt1, unroundedCityMpgFt1, unroundedHighwayMpgFt1, combinedMpg, annualFuelCostFt1,
                        annualConsumptionInBarrelsFt1, tailpipeCo2InGramsMileFt1);

                fuelData.setManufacturer(manufacturer);
                manufacturer.getFuelData().add(fuelData);
                fuelDataList.add(fuelData);
            }
            log.info("Loaded {} fuel data records successfully.", fuelDataList.size());

        } catch (Exception e) {
            log.error("Error parsing CSV file", e);
        }
        return fuelDataList;
    }

    private Integer parseInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private BigDecimal parseBigDecimal(String value) {
        try {
            return new BigDecimal(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
