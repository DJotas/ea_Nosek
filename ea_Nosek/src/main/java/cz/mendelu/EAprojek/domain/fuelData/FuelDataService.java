package cz.mendelu.EAprojek.domain.fuelData;

import cz.mendelu.EAprojek.domain.manufacturer.Manufacturer;
import cz.mendelu.EAprojek.domain.manufacturer.ManufacturerService;
import cz.mendelu.EAprojek.utils.exceptions.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FuelDataService {

    private FuelDataRepository fuelDataRepository;
    private ManufacturerService manufacturerService;

    public FuelDataService(FuelDataRepository fuelDataRepository,ManufacturerService manufacturerService) {
        this.fuelDataRepository = fuelDataRepository;
        this.manufacturerService = manufacturerService;
    }

    public List<FuelData> getAllFuelData() {
        List<FuelData> fuelData = new ArrayList<>();
        fuelDataRepository.findAll().forEach(fuelData::add);
        return fuelData;
    }

    public Page<FuelData> getFuelData(FuelDataFilter filter, Pageable pageable) {
        return fuelDataRepository.findAll(FuelDataSpecification.byFilter(filter), pageable);
    }

    public FuelData CreateFuelData(FuelData fuelData) {
        Optional<Manufacturer> optionalManufacturer = manufacturerService.getManufacturerByName(fuelData.getMake());
        if (!optionalManufacturer.isPresent()) {
            throw new NotFoundException();
        }
        Manufacturer manufacturer = optionalManufacturer.get();
        fuelData.setManufacturer(manufacturer);
        manufacturer.addFuelData(fuelData);
        return fuelDataRepository.save(fuelData);
    }

    public Optional<FuelData> getFuelDataById(Long id) {
        return fuelDataRepository.findById(id);
    }

    public FuelData updateFuelData(Long id,FuelData fuelData) {
        fuelData.setId(id);
        return fuelDataRepository.save(fuelData);
    }

    public void deleteFuelData(Long id) {
        fuelDataRepository.deleteById(id);
    }

    public void saveAll(List<FuelData> fuelData) {
        fuelDataRepository.saveAll(fuelData);
    }
}
