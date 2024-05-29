package me.caua.egiftstore.resource;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import me.caua.egiftstore.dto.in.GiftCardDTO;
import me.caua.egiftstore.dto.in.GiftCodeDTO;
import me.caua.egiftstore.dto.in.GiftCompanyDTO;
import me.caua.egiftstore.dto.out.GiftCardResponseDTO;
import me.caua.egiftstore.dto.out.GiftCompanyResponseDTO;
import me.caua.egiftstore.enums.GiftState;
import me.caua.egiftstore.service.GiftCardService;
import me.caua.egiftstore.service.GiftCompanyService;
import me.caua.egiftstore.utils.TestUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class GiftCardResourceTest {

    @Inject
    GiftCardService giftCardService;
    @Inject
    GiftCompanyService giftCompanyService;
    @Inject
    TestUtils testUtils;

    @Test
    public void createTest() {
        GiftCompanyResponseDTO giftCompanyResponseDTO = testUtils.createFakeCompany();

        GiftCodeDTO giftCodeDTO =
                new GiftCodeDTO("codigoteste1", GiftState.AVAILABLE, null);
        GiftCodeDTO giftCodeDTO2 =
                new GiftCodeDTO("codigoteste2", GiftState.AVAILABLE, null);


        GiftCardDTO giftCardDTO =
                new GiftCardDTO(
                        "Gift Card Google",
                        "Gift Card para a Play Store",
                        40.0,
                        giftCompanyResponseDTO.id(),
                        List.of("tag1", "tag2"),
                        List.of(giftCodeDTO, giftCodeDTO2),
                        true
                        );

        given()
                .header("Authorization", "Bearer " + testUtils.getAuth())
                .contentType(MediaType.APPLICATION_JSON)
                .body(giftCardDTO)
                .when()
                .post("/giftcard")
                .then()
                .statusCode(201)
                .body("name", is(giftCardDTO.name()));
    }

    @Test
    public void updateTest() {
        GiftCardResponseDTO response = createFakeGiftCard("update");

        GiftCodeDTO giftCodeDTO =
                new GiftCodeDTO("codigoteste4", GiftState.AVAILABLE, null);
        GiftCodeDTO giftCodeDTO2 =
                new GiftCodeDTO("codigoteste5", GiftState.AVAILABLE, null);


        GiftCardDTO giftCardDTO =
                new GiftCardDTO(
                        "Gift Card Google Editado",
                        "Gift Card para a Play Store Editado",
                        40.0,
                        1L,
                        List.of("tag1", "tag2"),
                        List.of(giftCodeDTO, giftCodeDTO2),
                        true
                );

        given()
                .header("Authorization", "Bearer " + testUtils.getAuth())
                .contentType(MediaType.APPLICATION_JSON)
                .body(giftCardDTO)
                .when()
                .pathParam("id", response.id())
                .put("/giftcard/{id}")
                .then()
                .statusCode(204);

        giftCardService.delete(response.id());
    }

    @Test
    public void findAllTest() {
        given()
            .when()
            .get("/giftcard")
            .then()
            .statusCode(200);
    }

    @Test
    public void findByIdTest() {
        GiftCardResponseDTO response = createFakeGiftCard("findId");

        given()
                .when()
                .pathParam("id", response.id())
                .get("/giftcard/{id}")
                .then()
                .statusCode(200)
                .body("id", is(response.id().intValue()));

        giftCardService.delete(response.id());
    }

    @Test
    public void findByNameTest() {
        GiftCardResponseDTO response = createFakeGiftCard("findName");

        given()
                .when()
                .pathParam("name", response.name())
                .get("/giftcard/search/name/{name}")
                .then()
                .statusCode(200)
                .body("name", hasItem(response.name()));

        giftCardService.delete(response.id());
    }

    @Test
    public void deleteTest() {
        GiftCardResponseDTO response = createFakeGiftCard("delete");

        given()
                .header("Authorization", "Bearer " + testUtils.getAuth())
                .when()
                .pathParam("id", response.id())
                .delete("/giftcard/{id}")
                .then()
                .statusCode(204);
    }

    public GiftCardResponseDTO createFakeGiftCard(String uniqueId) {
        GiftCompanyResponseDTO giftCompanyResponseDTO = testUtils.createFakeCompany();

        GiftCodeDTO giftCodeDTO =
                new GiftCodeDTO("codigoteste " + uniqueId, GiftState.AVAILABLE, null);
        GiftCodeDTO giftCodeDTO2 =
                new GiftCodeDTO("codigoteste " + uniqueId, GiftState.AVAILABLE, null);


        GiftCardDTO giftCardDTO =
                new GiftCardDTO(
                        "Gift Card Google Teste ",
                        "Gift Card para a Play Store Teste",
                        40.0,
                        giftCompanyResponseDTO.id(),
                        List.of("tag1", "tag2"),
                        List.of(giftCodeDTO, giftCodeDTO2),
                        true
                );

        return giftCardService.create(giftCardDTO);
    }
}
