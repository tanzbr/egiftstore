package me.caua.egiftstore.resource;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import me.caua.egiftstore.dto.in.GiftCardDTO;
import me.caua.egiftstore.dto.in.GiftCodeDTO;
import me.caua.egiftstore.dto.in.GiftCompanyDTO;
import me.caua.egiftstore.dto.in.ImageDTO;
import me.caua.egiftstore.dto.out.GiftCardResponseDTO;
import me.caua.egiftstore.dto.out.GiftCodeResponseDTO;
import me.caua.egiftstore.dto.out.GiftCompanyResponseDTO;
import me.caua.egiftstore.enums.GiftState;
import me.caua.egiftstore.service.GiftCardService;
import me.caua.egiftstore.service.GiftCodeService;
import me.caua.egiftstore.service.GiftCompanyService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class GiftCodeResourceTest {

    @Inject
    GiftCodeService giftCodeService;
    @Inject
    GiftCardService giftCardService;
    @Inject
    GiftCompanyService giftCompanyService;
    @Test
    public void createTest() {
        GiftCardResponseDTO response = createFakeGiftCard("create");

        GiftCodeDTO giftCodeDTO =
                new GiftCodeDTO("codigo teste create", GiftState.AVAILABLE, response.id());

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(giftCodeDTO)
                .when()
                .post("/giftcode")
                .then()
                .statusCode(201)
                .body("giftCode", is(giftCodeDTO.giftCode()));
    }

    @Test
    public void updateTest() {
        GiftCodeResponseDTO response = createFakeGiftCode("testeUpdate");

        GiftCodeDTO giftCodeDTO2 =
                new GiftCodeDTO("codigo teste update", GiftState.CLAIMED, response.giftcardId());

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(giftCodeDTO2)
                .when()
                .pathParam("id", response.id())
                .put("/giftcode/{id}")
                .then()
                .statusCode(204);

        giftCardService.delete(response.giftcardId());
    }

    @Test
    public void findAllTest() {
        given()
            .when()
            .get("/giftcode")
            .then()
            .statusCode(200);
    }

    @Test
    public void findByIdTest() {
        GiftCodeResponseDTO response = createFakeGiftCode("testeFindId");

        given()
                .when()
                .pathParam("id", response.id())
                .get("/giftcode/{id}")
                .then()
                .statusCode(200)
                .body("id", is(response.id().intValue()));

        giftCardService.delete(response.giftcardId());
    }

    @Test
    public void findByGiftCardTest() {
        GiftCodeResponseDTO response = createFakeGiftCode("testeFindByGift");

        given()
                .when()
                .pathParam("id", response.giftcardId())
                .get("/giftcode/search/giftcard/{id}")
                .then()
                .statusCode(200)
                .body("giftCode", hasItem(response.giftCode()));

        giftCardService.delete(response.giftcardId());
    }

    @Test
    public void deleteTest() {
        GiftCodeResponseDTO response = createFakeGiftCode("testeDelete");

        given()
                .when()
                .pathParam("id", response.id())
                .delete("/giftcode/{id}")
                .then()
                .statusCode(204);
    }

    public GiftCodeResponseDTO createFakeGiftCode(String uniqueId) {
        GiftCardResponseDTO response = createFakeGiftCard(uniqueId);

        GiftCodeDTO giftCodeDTO =
                new GiftCodeDTO("codigo teste giftcode " + uniqueId, GiftState.AVAILABLE, response.id());

        return giftCodeService.create(giftCodeDTO);
    }

    public GiftCardResponseDTO createFakeGiftCard(String uniqueId) {
        GiftCompanyResponseDTO giftCompanyResponseDTO = createFakeCompany();

        ImageDTO imageDTO =
                new ImageDTO("Imagem teste 5", "https://url teste", 1);
        ImageDTO imageDTO2 =
                new ImageDTO("Imagem teste 4", "https://url teste", 2);
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
                        List.of(imageDTO, imageDTO2),
                        List.of(giftCodeDTO, giftCodeDTO2),
                        true
                );

        return giftCardService.create(giftCardDTO);
    }

    public GiftCompanyResponseDTO createFakeCompany() {
        ImageDTO imageDTO =
                new ImageDTO("Imagem teste", "https://url teste", 1);

        GiftCompanyDTO giftCompanyDTO
                = new GiftCompanyDTO("Teste", "111.111.111", imageDTO);

        return giftCompanyService.create(giftCompanyDTO);
    }
}
