package org;

import io.quarkus.test.junit.QuarkusTest;
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
                .statusCode(200)
                .body(is("pets"));
    }
}
