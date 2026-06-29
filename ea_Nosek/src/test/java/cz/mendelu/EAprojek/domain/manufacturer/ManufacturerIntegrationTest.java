package cz.mendelu.EAprojek.domain.manufacturer;

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

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql("/test-data/cleanup.sql")
@Sql("/test-data/base-data.sql")
public class ManufacturerIntegrationTest {

    private final static String BASE_URI = "http://localhost";

    @LocalServerPort
    private int port;

    @BeforeEach
    public void configureRestAssured() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.port = port;
    }

    @Test
    public void testGetAllManufacturers() {
        given()
                .when()
                .get("/manufacturers")
                .then()
                .statusCode(200)
                .body("items.size()", is(2))
                .body("items.id", containsInAnyOrder(1, 2))
                .body("items.name", containsInAnyOrder("Toyota", "Ford"))
                .body("items.countryOfOrigin", containsInAnyOrder("Japan", "USA"));
    }

    @Test
    public void testCreateManufacturer() {
        var newManufacturer = new ManufacturerRequest("New Manufacturer", "New Country", 2020);
        int id = given()
                .contentType(ContentType.JSON)
                .body(newManufacturer)
                .when()
                .post("/manufacturers")
                .then()
                .statusCode(201)
                .extract()
                .path("content.id");

        when()
                .get("/manufacturers/" + id)
                .then()
                .statusCode(200)
                .body("content.id", is(id))
                .body("content.name", is("New Manufacturer"))
                .body("content.countryOfOrigin", is("New Country"))
                .body("content.yearEstablished", is(2020));
    }

    @Test
    public void testCreateManufacturer_BadRequest() {
        var newManufacturer = new ManufacturerRequest(null, "New Country", 2020);

        given()
                .contentType(ContentType.JSON)
                .body(newManufacturer)
                .when()
                .post("/manufacturers")
                .then()
                .statusCode(400);
    }

    @Test
    public void testGetManufacturerById() {
        given()
                .when()
                .get("/manufacturers/1")
                .then()
                .statusCode(200)
                .body("content.id", is(1))
                .body("content.name", is("Toyota"))
                .body("content.countryOfOrigin", is("Japan"))
                .body("content.yearEstablished", is(1937))
                .body("content.averCo2Emission", is(215.0F))
                .body("content.averFuelCost", is(1250.0F));
    }

    @Test
    public void testGetManufacturerById_NotFound() {
        given()
                .when()
                .get("/manufacturers/999")
                .then()
                .statusCode(404);
    }

    @Test
    public void testUpdateManufacturer() {
        var updatedManufacturer = new ManufacturerRequest("Updated Manufacturer", "Updated Country", 1990);
        given()
                .contentType(ContentType.JSON)
                .body(updatedManufacturer)
                .when()
                .put("/manufacturers/1")
                .then()
                .statusCode(202);

        when()
                .get("/manufacturers/1")
                .then()
                .statusCode(200)
                .body("content.id", is(1))
                .body("content.name", is("Updated Manufacturer"))
                .body("content.countryOfOrigin", is("Updated Country"))
                .body("content.yearEstablished", is(1990));
    }

    @Test
    public void testUpdateManufacturer_NotFound() {
        var updatedManufacturer = new ManufacturerRequest("Updated Manufacturer", "Updated Country", 1990);
        given()
                .contentType(ContentType.JSON)
                .body(updatedManufacturer)
                .when()
                .put("/manufacturers/999")
                .then()
                .statusCode(404);
    }

    @Test
    public void testDeleteManufacturer() {
        given()
                .when()
                .delete("/manufacturers/1")
                .then()
                .statusCode(204);

        when()
                .get("/manufacturers/1")
                .then()
                .statusCode(404);
    }
}