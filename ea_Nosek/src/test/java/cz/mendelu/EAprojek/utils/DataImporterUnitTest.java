package cz.mendelu.EAprojek.utils;

import cz.mendelu.EAprojek.domain.fuelData.FuelData;
import cz.mendelu.EAprojek.domain.manufacturer.Manufacturer;
import cz.mendelu.EAprojek.utils.data.DataImporter;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DataImporterUnitTest {

    @Test
    public void testParseFuelData() throws IOException {
        Resource resource = mock(Resource.class);
        when(resource.getFile()).thenReturn(new File("src/test/resources/data/fuelData.csv"));

        DataImporter dataImporter = new DataImporter(null, null, null);

        List<FuelData> fuelDataList = dataImporter.parseFuelData(resource);

        assertThat(fuelDataList, hasSize(0));
        //assertThat(fuelDataList.get(0).getMake(), equalTo("Toyota"));
        //assertThat(fuelDataList.get(0).getYear(), equalTo(2020));
    }

    @Test
    public void testParseFuelData_EmptyFile() throws IOException {
        Resource resource = mock(Resource.class);
        when(resource.getFile()).thenReturn(new File("src/test/resources/data/empty.csv"));

        DataImporter dataImporter = new DataImporter(null, null, null);

        List<FuelData> fuelDataList = dataImporter.parseFuelData(resource);

        assertThat(fuelDataList, hasSize(0));
    }

    @Test
    public void testParseFuelData_NonExistingFile() throws IOException {
        Resource resource = mock(Resource.class);
        when(resource.getFile()).thenReturn(new File("src/test/resources/data/non-existing.csv"));

        DataImporter dataImporter = new DataImporter(null, null, null);

        List<FuelData> fuelDataList = dataImporter.parseFuelData(resource);

        assertThat(fuelDataList, hasSize(0));
    }

    @Test
    public void testParseManufacturers() throws IOException {
        Resource resource = mock(Resource.class);
        when(resource.getFile()).thenReturn(new File("src/test/resources/data/manufacturers.csv"));

        DataImporter dataImporter = new DataImporter(null, null, null);

        List<Manufacturer> manufacturerList = dataImporter.parseManufacturers(resource);

        assertThat(manufacturerList, hasSize(3));
        assertThat(manufacturerList.get(0).getName(), equalTo("Toyota"));
        assertThat(manufacturerList.get(0).getCountryOfOrigin(), equalTo("Japan"));
    }

    @Test
    public void testParseManufacturers_EmptyFile() throws IOException {
        Resource resource = mock(Resource.class);
        when(resource.getFile()).thenReturn(new File("src/test/resources/data/empty.csv"));

        DataImporter dataImporter = new DataImporter(null, null, null);

        List<Manufacturer> manufacturerList = dataImporter.parseManufacturers(resource);

        assertThat(manufacturerList, hasSize(0));
    }

    @Test
    public void testParseManufacturers_NonExistingFile() throws IOException {
        Resource resource = mock(Resource.class);
        when(resource.getFile()).thenReturn(new File("src/test/resources/data/non-existing.csv"));

        DataImporter dataImporter = new DataImporter(null, null, null);

        List<Manufacturer> manufacturerList = dataImporter.parseManufacturers(resource);

        assertThat(manufacturerList, hasSize(0));
    }
}
