package me.caua.egiftstore.resource;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.MediaType;
import me.caua.egiftstore.dto.in.*;
import me.caua.egiftstore.dto.out.*;
import me.caua.egiftstore.enums.GiftState;
import me.caua.egiftstore.enums.PaymentStatus;
import me.caua.egiftstore.enums.Role;
import me.caua.egiftstore.service.CustomerService;
import me.caua.egiftstore.service.EmployeeService;
import me.caua.egiftstore.service.GiftCardService;
import me.caua.egiftstore.service.OrderService;
import me.caua.egiftstore.utils.TestUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class OrderResourceTest {

    @Inject
    GiftCardService giftCardService;
    @Inject
    OrderService orderService;
    @Inject
    CustomerService customerService;
    @Inject
    TestUtils testUtils;

//    OrderResponseDTO create(@Valid OrderDTO orderDTO);
//    OrderResponseDTO findById(Long id);
//    List<OrderResponseDTO> findAll();
//    List<OrderResponseDTO> findByCustomerId(Long customerId);

    @Test
    public void createTest() {
        given()
                .header("Authorization", "Bearer " + testUtils.getAuth())
                .contentType(MediaType.APPLICATION_JSON)
                .body(createFakeOrder("cpfteste1"))
                .when()
                .post("/order")
                .then()
                .statusCode(201);
    }

    @Test
    public void findAllTest() {
        given()
                .header("Authorization", "Bearer " + testUtils.getAuth())
                .when()
                .get("/order")
                .then()
                .statusCode(200);
    }

    @Test
    public void findByIdTest() {
        OrderResponseDTO response = orderService.create(createFakeOrder("cpfteste2"));

        given()
                .header("Authorization", "Bearer " + testUtils.getAuth())
                .when()
                .pathParam("id", response.id())
                .get("/order/{id}")
                .then()
                .statusCode(200)
                .body("id", is(response.id().intValue()));

        orderService.delete(response.id());
    }

    private OrderDTO createFakeOrder(String cpf) {
        CustomerResponseDTO customer = createFakeCustomer(cpf);
        GiftCardResponseDTO giftcard = createFakeGiftCard("gifttest"+cpf);

        List<OrderItemDTO> orderItems = new ArrayList<>();
        orderItems.add(new OrderItemDTO(
                giftcard.price(),
                0.0,
                1,
                giftcard.id()
        ));

        return new OrderDTO(
                LocalDateTime.now(),
                customer.id(),
                orderItems,
                new PaymentDTO(0.0, PaymentStatus.PENDING)
        );
    }

    public CustomerResponseDTO createFakeCustomer(String cpf) {
        UserDTO userDTO =
                new UserDTO(
                        "Cliente2 Teste",
                        cpf,
                        "cliente2@teste.com",
                        "cliente2teste",
                        "senhateste2",
                        true,
                        LocalDate.now()
                );

        CustomerDTO customerDTO = new CustomerDTO(
                true,
                userDTO
        );

        return customerService.create(customerDTO);
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
