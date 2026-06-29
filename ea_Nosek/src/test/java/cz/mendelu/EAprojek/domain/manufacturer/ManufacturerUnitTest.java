package cz.mendelu.EAprojek.domain.manufacturer;

import cz.mendelu.EAprojek.domain.fuelData.FuelData;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ManufacturerUnitTest {
    @Test
    public void testGetAverCo2Emission() {
        Manufacturer manufacturer = new Manufacturer();
        FuelData fuelData1 = new FuelData();
        FuelData fuelData2 = new FuelData();
        fuelData2.setTailpipeCo2InGramsMileFt1(BigDecimal.valueOf(10));
        fuelData1.setTailpipeCo2InGramsMileFt1(BigDecimal.valueOf(10));
        manufacturer.addFuelData(fuelData1);
        manufacturer.addFuelData(fuelData2);


        BigDecimal expected = BigDecimal.valueOf(10);
        BigDecimal actual = manufacturer.getAverCo2Emission();

        assertThat(actual, is(expected));
    }


    @Test
    public void testGetAverCo2Emission_EmptyFuelData() {
        Manufacturer manufacturer = new Manufacturer();


        BigDecimal expected = BigDecimal.ZERO;
        BigDecimal actual = manufacturer.getAverCo2Emission();

        assertThat(actual, is(expected));
    }

    @Test
    public void testGetAverFuelCost() {
        Manufacturer manufacturer = new Manufacturer();
        FuelData fuelData1 = new FuelData();
        FuelData fuelData2 = new FuelData();
        fuelData2.setAnnualFuelCostFt1(BigDecimal.valueOf(15.6));
        fuelData1.setAnnualFuelCostFt1(BigDecimal.valueOf(25.5));
        manufacturer.addFuelData(fuelData1);
        manufacturer.addFuelData(fuelData2);


        BigDecimal expected = BigDecimal.valueOf(20.6);
        BigDecimal actual = manufacturer.getAverFuelCost();

        assertThat(actual, is(expected));
    }

    @Test
    public void testGetAverFuelCost_EmptyFuelData() {
        Manufacturer manufacturer = new Manufacturer();

        BigDecimal expected = BigDecimal.ZERO;
        BigDecimal actual = manufacturer.getAverFuelCost();

        assertThat(actual, is(expected));
    }



    @Test
    public void testGetAverYearCo2EmissionPerDriver() {
        Manufacturer manufacturer = new Manufacturer();
        FuelData fuelData1 = new FuelData();
        FuelData fuelData2 = new FuelData();
        fuelData2.setTailpipeCo2InGramsMileFt1(BigDecimal.valueOf(15.4));
        fuelData1.setTailpipeCo2InGramsMileFt1(BigDecimal.valueOf(20.6));
        manufacturer.addFuelData(fuelData1);
        manufacturer.addFuelData(fuelData2);

        BigDecimal expected = BigDecimal.valueOf(256734.0);
        BigDecimal actual = manufacturer.getAverYearCo2Emission();

        assertThat(actual, is(expected));
    }

    @Test
    public void testGetAverYearCo2EmissionPerDriver_EmptyFuelData() {
        Manufacturer manufacturer = new Manufacturer();

        BigDecimal expected = BigDecimal.ZERO;
        BigDecimal actual = manufacturer.getAverYearCo2Emission();

        assertThat(actual, is(expected));
    }

    @Test
    public void testGetAverCombinedMpg() {
        Manufacturer manufacturer = new Manufacturer();
        FuelData fuelData1 = new FuelData();
        FuelData fuelData2 = new FuelData();
        fuelData1.setCombinedMpg(20);
        fuelData2.setCombinedMpg(30);
        manufacturer.addFuelData(fuelData1);
        manufacturer.addFuelData(fuelData2);

        Integer expected = 25;
        Integer actual = manufacturer.getAverCombinedMpg();

        assertThat(actual, is(expected));
    }

    @Test
    public void testGetAverCombinedMpg_empty() {
        Manufacturer manufacturer = new Manufacturer();

        Integer expected = 0;
        Integer actual = manufacturer.getAverCombinedMpg();

        assertThat(actual, is(expected));
    }

    @Test
    public void testGetAverEngineIndex() {
        Manufacturer manufacturer = new Manufacturer();
        FuelData fuelData1 = new FuelData();
        FuelData fuelData2 = new FuelData();
        fuelData1.setEngineIndex(1);
        fuelData2.setEngineIndex(3);
        manufacturer.addFuelData(fuelData1);
        manufacturer.addFuelData(fuelData2);

        Integer expected = 2;
        Integer actual = manufacturer.getAverEngineIndex();

        assertThat(actual, is(expected));
    }

    @Test
    public void testGetAverEngineIndex_empty() {
        Manufacturer manufacturer = new Manufacturer();

        Integer expected = 0;
        Integer actual = manufacturer.getAverEngineIndex();

        assertThat(actual, is(expected));
    }



}
