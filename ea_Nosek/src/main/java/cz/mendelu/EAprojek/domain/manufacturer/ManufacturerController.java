package cz.mendelu.EAprojek.domain.manufacturer;


import cz.mendelu.EAprojek.domain.manufacturer.ManufacturerService;
import cz.mendelu.EAprojek.utils.response.ArrayResponse;
import cz.mendelu.EAprojek.utils.response.ObjectResponse;
import cz.mendelu.EAprojek.utils.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("manufacturers")
@Validated
@Tag(name = "Manufacturers", description = "Operations on manufacturers")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    @Autowired
    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @Operation(summary = "Get all manufacturers", description = "Retrieve all manufacturers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved manufacturers"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = "", produces = "application/json")
    @Valid
    @Cacheable("manufacturers")
    public ArrayResponse<ManufacturerResponse> getManufacturers() {
        return ArrayResponse.of(
                manufacturerService.getAllManufacturers(),
                ManufacturerResponse::new
        );
    }

    @Operation(summary = "Create a manufacturer", description = "Create a new manufacturer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created manufacturer"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping(value = "", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @Valid
    @CacheEvict(value = "manufacturers", allEntries = true)
    public ObjectResponse<ManufacturerResponse> createManufacturer(
            @RequestBody @Valid ManufacturerRequest manufacturerRequest
    ) {
        Manufacturer manufacturer = new Manufacturer();
        manufacturerRequest.toManufacturer(manufacturer);

        manufacturerService.createManufacturer(manufacturer);

        return ObjectResponse.of(manufacturer, ManufacturerResponse::new);
    }

    @Operation(summary = "Get manufacturer by ID", description = "Retrieve a manufacturer by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved manufacturer"),
            @ApiResponse(responseCode = "404", description = "Manufacturer not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = "/{id}", produces = "application/json")
    @Valid
    public ObjectResponse<ManufacturerResponse> getManufacturer(@PathVariable Long id) {
        Manufacturer manufacturer = manufacturerService
                .getManufacturerById(id)
                .orElseThrow(NotFoundException::new);
        return ObjectResponse.of(manufacturer, ManufacturerResponse::new);
    }


    @Operation(summary = "Update a manufacturer", description = "Update an existing manufacturer by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Successfully updated manufacturer"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Manufacturer not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping(value = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Valid
    @Transactional
    @CacheEvict(value = "manufacturers", allEntries = true)
    public ObjectResponse<ManufacturerResponse> updateManufacturer(@PathVariable Long id, @RequestBody @Valid ManufacturerRequest manufacturerRequest) {
        Manufacturer manufacturer = manufacturerService.getManufacturerById(id)
                .orElseThrow(NotFoundException::new);
        manufacturerRequest.toManufacturer(manufacturer);

        manufacturerService.updateManufacturer(id, manufacturer);

        return ObjectResponse.of(manufacturer, ManufacturerResponse::new);
    }

    @Operation(summary = "Delete a manufacturer", description = "Delete a manufacturer by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted manufacturer"),
            @ApiResponse(responseCode = "404", description = "Manufacturer not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping(value = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = "manufacturers", allEntries = true)
    public void deleteManufacturer(@PathVariable Long id) {
        manufacturerService
                .getManufacturerById(id)
                .ifPresent(manufacturer -> {
                    manufacturerService.deleteManufacturer(id);
                });
    }
}
