package cz.mendelu.EAprojek.domain.fuelData;


import cz.mendelu.EAprojek.domain.manufacturer.ManufacturerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;

import cz.mendelu.EAprojek.utils.response.ArrayResponse;
import cz.mendelu.EAprojek.utils.response.ObjectResponse;
import cz.mendelu.EAprojek.utils.response.PageResponse;
import cz.mendelu.EAprojek.utils.exceptions.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.List;

@RestController
@RequestMapping("fuelData")
@Validated
@Tag(name = "Fuel data", description = "Operations on fuel data")
public class FuelDataController {

    private final FuelDataService fuelDataService;
    private final ManufacturerService manufacturerService;

    @Autowired
    public FuelDataController(FuelDataService fuelDataService,ManufacturerService manufacturerService) {
        this.fuelDataService = fuelDataService;
        this.manufacturerService = manufacturerService;
    }

    @Operation(summary = "Get all fuel data", description = "Retrieve all fuel data records")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of fuel data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
   @GetMapping(value = "", produces = "application/json")
   @Valid
    public PageResponse<FuelDataResponse> getFuelData(
        FuelDataFilter filter,
        @PageableDefault(
                size = 20,
                sort = "id",
                direction = Sort.Direction.ASC
        ) Pageable pageable
    ) {
    return PageResponse.of(
            fuelDataService.getFuelData(filter, pageable),
            FuelDataResponse::new
        );
    }

    @Operation(summary = "Create new fuel data", description = "Create a new record for fuel data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created new fuel data"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping(value = "", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @Valid
    @CacheEvict(value = "fuelData", allEntries = true)
    public ObjectResponse<FuelDataResponse> createFuelData(
            @RequestBody @Valid FuelDataRequest fuelDataRequest
    ) {
        FuelData fuelData = new FuelData();
        fuelDataRequest.toFuelData(fuelData);

        fuelDataService.CreateFuelData(fuelData);

        return ObjectResponse.of(fuelData, FuelDataResponse::new);
    }

    @Operation(summary = "Get fuel data by ID", description = "Retrieve a specific fuel data record by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the fuel data"),
            @ApiResponse(responseCode = "404", description = "Fuel data not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = "/{id}", produces = "application/json")
    @Valid
    public ObjectResponse<FuelDataResponse> getFuelData(@PathVariable Long id) {
        FuelData fuelData = fuelDataService
                .getFuelDataById(id)
                .orElseThrow(NotFoundException::new);
        return ObjectResponse.of(fuelData, FuelDataResponse::new);
    }

    @Operation(summary = "Update fuel data", description = "Update an existing fuel data record by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Successfully updated the fuel data"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Fuel data not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping(value = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Valid
    @Transactional
    @CacheEvict(value = "fuelData", allEntries = true)
    public ObjectResponse<FuelDataResponse> updateFuelData(@PathVariable Long id, @RequestBody @Valid FuelDataRequest fuelDataRequest) {
        FuelData fuelData = fuelDataService.getFuelDataById(id)
                .orElseThrow(NotFoundException::new);
        fuelDataRequest.toFuelData(fuelData);

        fuelDataService.updateFuelData(id, fuelData);

        return ObjectResponse.of(fuelData, FuelDataResponse::new);
    }


    @Operation(summary = "Delete fuel data", description = "Delete an existing fuel data record by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted the fuel data"),
            @ApiResponse(responseCode = "404", description = "Fuel data not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping(value = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = "fuelData", allEntries = true)
    public void deleteFuelData(@PathVariable Long id) {
        fuelDataService
                .getFuelDataById(id)
                .ifPresent(fuelData -> {
                    fuelDataService.deleteFuelData(id);
                });
    }
}
