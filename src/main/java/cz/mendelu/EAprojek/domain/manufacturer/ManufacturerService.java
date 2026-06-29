package cz.mendelu.EAprojek.domain.manufacturer;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerService {

    private final ManufacturerRepository repository;

    public ManufacturerService(ManufacturerRepository repository){
        this.repository = repository;
    }


    public List<Manufacturer> getAllManufacturers(){
        List<Manufacturer> manufacturers = new ArrayList<>();
        repository.findAll().forEach(manufacturers::add);
        return manufacturers;
    }

    public Optional<Manufacturer>  getManufacturerByName(String name){
        return repository.findByName(name);
    }

    public Manufacturer createManufacturer(Manufacturer manufacturer){
        return repository.save(manufacturer);
    }

    public Optional<Manufacturer> getManufacturerById(Long id){
        return repository.findById(id);
    }

    public Manufacturer updateManufacturer(Long id, Manufacturer manufacturer){
        manufacturer.setId(id);
        return repository.save(manufacturer);
    }

    public void deleteManufacturer(Long id){
        repository.deleteById(id);
    }

    public void saveAll(List<Manufacturer> manufacturers) {
        repository.saveAll(manufacturers);
    }
}
