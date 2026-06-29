package cz.mendelu.EAprojek.domain.fuelData;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigDecimal;
import java.util.List;

public interface FuelDataRepository extends JpaRepository<FuelData, Long>, JpaSpecificationExecutor<FuelData> {

    int countByCombinedMpgGreaterThan(Integer combinedMpg);

    @Query("SELECT COUNT(f) FROM FuelData f")
    int countFuelData();

    Iterable<FuelData> findByTailpipeCo2InGramsMileFt1IsLessThan(BigDecimal tailpipeCo2InGramsMileFt1);

    Iterable<FuelData> findTop3ByOrderByAnnualFuelCostFt1Asc();

    @Query("SELECT f.manufacturer.name, f.vehicleClass, COUNT(f) as classCount " +
            "FROM FuelData f " +
            "GROUP BY f.manufacturer.name, f.vehicleClass " +
            "ORDER BY f.manufacturer.name, classCount DESC")
    List<Object[]> findMostCommonVehicleClass();

    @Query("SELECT f.manufacturer.name, AVG(f.combinedMpg) " +
            "FROM FuelData f " +
            "GROUP BY f.manufacturer.name")
    List<Object[]> findAverageMpgByManufacturer();
}
