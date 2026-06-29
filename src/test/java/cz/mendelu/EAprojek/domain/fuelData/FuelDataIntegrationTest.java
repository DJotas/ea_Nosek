package cz.mendelu.EAprojek.domain.fuelData;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql("/test-data/cleanup.sql")
@Sql("/test-data/base-data.sql")
public class FuelDataIntegrationTest {

    private final static String BASE_URI = "http://localhost";

    @LocalServerPort
    private int port;

    @BeforeEach
    public void configureRestAssured() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.port = port;
    }

    @Test
    public void testGetAllFuelData() {
        given()
                .when()
                .get("/fuelData")
                .then()
                .statusCode(200)
                .body("items.size()", is(4))
                .body("items.id", containsInAnyOrder(1, 2, 3, 4))
                .body("items.make", containsInAnyOrder("Toyota", "Ford","Toyota", "Ford"))
                .body("items.model", containsInAnyOrder("Camry", "F-150", "Camry", "Focus"));
    }

    @Test
    public void testGetFuelDataWithPagination() {
    given()
            .when()
            .get("/fuelData?page=0&size=2")
            .then()
            .statusCode(200)
            .body("items.size()", is(2))
            .body("page", is(0))
            .body("size", is(2))
            .body("totalItems", is(4))
            .body("totalPages", is(2));
}

    @Test
    public void testGetFuelDataWithMakeFilter() {
    given()
            .when()
            .get("/fuelData?make=Toyota")
            .then()
            .statusCode(200)
            .body("items.size()", is(2))
            .body("items.make", containsInAnyOrder("Toyota", "Toyota"));
}

    @Test
    public void testCreateFuelData() {
        var newFuelData = new FuelDataRequest(
                2022, "Toyota", "Camry", "Sedan", "FWD", "Automatic", 1,
                "V6", new BigDecimal("6.0"), new BigDecimal("3.5"),
                "Yes", "Gasoline", 30, 40, new BigDecimal("30.5"),
                new BigDecimal("40.5"), 35, new BigDecimal("1200.00"),
                new BigDecimal("15.0"), new BigDecimal("300.0"));

        int id = given()
                .contentType(ContentType.JSON)
                .body(newFuelData)
                .when()
                .post("/fuelData")
                .then()
                .statusCode(201)
                .extract()
                .path("content.id");

        when()
                .get("/fuelData/" + id)
                .then()
                .statusCode(200)
                .body("content.id", is(id))
                .body("content.make", is("Toyota"))
                .body("content.model", is("Camry"))
                .body("content.year", is(2022));
    }

    @Test
    public void testCreateFuelData_BadRequest() {
        var newFuelData = new FuelDataRequest(
                2022, null, "Camry", "Sedan", "FWD", "Automatic", 1,
                "V6", new BigDecimal("6.0"), new BigDecimal("3.5"),
                "Yes", "Gasoline", 30, 40, new BigDecimal("30.5"),
                new BigDecimal("40.5"), 35, new BigDecimal("1200.00"),
                new BigDecimal("15.0"), new BigDecimal("300.0"));

        given()
                .contentType(ContentType.JSON)
                .body(newFuelData)
                .when()
                .post("/fuelData")
                .then()
                .statusCode(400);
    }

    @Test
    public void testGetFuelDataById() {
        given()
                .when()
                .get("/fuelData/1")
                .then()
                .statusCode(200)
                .body("content.id", is(1))
                .body("content.make", is("Toyota"))
                .body("content.model", is("Camry"));
    }

    @Test
    public void testGetFuelDataById_NotFound() {
        given()
                .when()
                .get("/fuelData/999")
                .then()
                .statusCode(404);
    }

    @Test
    public void testUpdateFuelData() {
        var updatedFuelData = new FuelDataRequest(
                2022, "Toyota", "Accord", "Sedan", "FWD", "Automatic", 1,
                "V6", new BigDecimal("6.0"), new BigDecimal("3.5"),
                "Yes", "Gasoline", 30, 40, new BigDecimal("30.5"),
                new BigDecimal("40.5"), 35, new BigDecimal("1200.00"),
                new BigDecimal("15.0"), new BigDecimal("300.0"));

        given()
                .contentType(ContentType.JSON)
                .body(updatedFuelData)
                .when()
                .put("/fuelData/1")
                .then()
                .statusCode(202);

        when()
                .get("/fuelData/1")
                .then()
                .statusCode(200)
                .body("content.id", is(1))
                .body("content.make", is("Toyota"))
                .body("content.model", is("Accord"))
                .body("content.year", is(2022));
    }

    @Test
    public void testUpdateFuelData_NotFound() {
        var updatedFuelData = new FuelDataRequest(
                2022, "Honda", "Accord", "Sedan", "FWD", "Automatic", 1,
                "V6", new BigDecimal("6.0"), new BigDecimal("3.5"),
                "Yes", "Gasoline", 30, 40, new BigDecimal("30.5"),
                new BigDecimal("40.5"), 35, new BigDecimal("1200.00"),
                new BigDecimal("15.0"), new BigDecimal("300.0"));

        given()
                .contentType(ContentType.JSON)
                .body(updatedFuelData)
                .when()
                .put("/fuelData/999")
                .then()
                .statusCode(404);
    }

    @Test
    public void testDeleteFuelData() {
        given()
                .when()
                .delete("/fuelData/1")
                .then()
                .statusCode(204);

        when()
                .get("/fuelData/1")
                .then()
                .statusCode(404);
    }
}
