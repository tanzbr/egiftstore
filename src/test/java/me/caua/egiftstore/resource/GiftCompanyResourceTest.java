package me.caua.egiftstore.resource;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import me.caua.egiftstore.dto.in.GiftCompanyDTO;
import me.caua.egiftstore.dto.in.ImageDTO;
import me.caua.egiftstore.dto.out.GiftCompanyResponseDTO;
import me.caua.egiftstore.service.GiftCompanyService;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

@QuarkusTest
class GiftCompanyResourceTest {

    @Inject
    GiftCompanyService giftCompanyService;

    @Test
    public void createTest() {
        ImageDTO imageDTO =
                new ImageDTO("logo do google", "https://url.com", 1);

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
        GiftCompanyResponseDTO response = createFakeCompany();

        ImageDTO imageDTO =
                new ImageDTO("logo do google update 2", "https://url.com", 2);

        GiftCompanyDTO giftCompanyDTO =
                new GiftCompanyDTO(
                        "Google Update",
                        "22.111.111-0001/10",
                        imageDTO);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(giftCompanyDTO)
                .when()
                .pathParam("id", response.id())
                .put("/giftcompany/{id}")
                .then()
                .statusCode(204);

        giftCompanyService.delete(response.id());
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
        GiftCompanyResponseDTO response = createFakeCompany();

        given()
                .when()
                .pathParam("id", response.id())
                .get("/giftcompany/{id}")
                .then()
                .statusCode(200)
                .body("id", is(response.id().intValue()));

        giftCompanyService.delete(response.id());
    }

    @Test
    public void findByNameTest() {
        GiftCompanyResponseDTO response = createFakeCompany();

        given()
                .when()
                .pathParam("name", response.name())
                .get("/giftcompany/search/name/{name}")
                .then()
                .statusCode(200)
                .body("name", hasItem(response.name()));

        giftCompanyService.delete(response.id());
    }

    @Test
    public void deleteTest() {
        GiftCompanyResponseDTO response = createFakeCompany();

        given()
                .when()
                .pathParam("id", response.id())
                .delete("/giftcompany/{id}")
                .then()
                .statusCode(204);
    }

    public GiftCompanyResponseDTO createFakeCompany() {
        ImageDTO imageDTO =
                new ImageDTO("Imagem teste", "https://url teste", 1);

        GiftCompanyDTO giftCompanyDTO
                = new GiftCompanyDTO("Teste", "111.111.111", imageDTO);

        return giftCompanyService.create(giftCompanyDTO);
    }
}
