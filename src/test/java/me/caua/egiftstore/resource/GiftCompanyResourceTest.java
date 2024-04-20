package me.caua.egiftstore.resource;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import me.caua.egiftstore.dto.GiftCompanyDTO;
import me.caua.egiftstore.dto.ImageDTO;
import me.caua.egiftstore.model.GiftCompany;
import me.caua.egiftstore.repository.GiftCompanyRepository;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

@QuarkusTest
class GiftCompanyResourceTest {

    @Test
    public void createTest() {
        ImageDTO imageDTO = new ImageDTO("logo do google", "https://url.com", 1);
        GiftCompanyDTO giftCompanyDTO =
                new GiftCompanyDTO(
                        "Google",
                        "11.111.111-0001/10",
                        imageDTO);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(giftCompanyDTO)
                .when()
                .post("/giftcompany")
                .then()
                .statusCode(201)
                .body("name", is(giftCompanyDTO.name()));
    }

    @Test
    public void updateTest() {
        ImageDTO imageDTO = new ImageDTO("logo do google update 2", "https://url.com", 2);
        GiftCompanyDTO giftCompanyDTO =
                new GiftCompanyDTO(
                        "Google Update",
                        "22.111.111-0001/10",
                        imageDTO);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(giftCompanyDTO)
                .when()
                .pathParam("id", 1)
                .put("/giftcompany/{id}")
                .then()
                .statusCode(204);
    }

    @Test
    public void findAllTest() {
        given()
            .when()
            .get("/giftcompany")
            .then()
            .statusCode(200);
    }

    @Test
    public void findByIdTest() {
        given()
                .when()
                .pathParam("id", 1)
                .get("/giftcompany/{id}")
                .then()
                .statusCode(200)
                .body("id", is(1));
    }

    @Test
    public void findByNameTest() {
        given()
                .when()
                .pathParam("name", "Google")
                .get("/giftcompany/search/name/{name}")
                .then()
                .statusCode(200)
                .body("name", hasItem(containsString("Google")));
    }

    @Test
    public void deleteTest() {
        given()
                .when()
                .pathParam("id", 1)
                .delete("/giftcompany/{id}")
                .then()
                .statusCode(204);
    }
}
