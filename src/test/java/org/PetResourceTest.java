package org;

import com.json.Pet;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class PetResourceTest {

    @Test
    public void testGetPets() {
        given()
                .when().get("/pets")
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetOnePet() {
        given()
                .contentType(ContentType.JSON).param("Tyga")
                .when().get("/pets")
                .then().statusCode(200);
    }

}