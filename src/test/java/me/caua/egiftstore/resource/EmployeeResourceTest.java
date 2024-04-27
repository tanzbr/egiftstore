package me.caua.egiftstore.resource;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import me.caua.egiftstore.dto.in.EmployeeDTO;
import me.caua.egiftstore.dto.in.UserDTO;
import me.caua.egiftstore.dto.out.EmployeeResponseDTO;
import me.caua.egiftstore.enums.Role;
import me.caua.egiftstore.service.EmployeeService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class EmployeeResourceTest {

    @Inject
    EmployeeService employeeService;

    @Test
    public void createTest() {
        UserDTO userDTO =
                new UserDTO(
                        "Usuário Teste",
                        "111.111",
                        "teste@teste.com",
                        "userteste",
                        "senhateste",
                        true,
                        LocalDate.now()
                );

        EmployeeDTO employeeDTO =
                new EmployeeDTO(
                        40.0,
                        1230.0,
                        LocalDate.now(),
                        userDTO,
                        Role.CUSTOMER_SERVICE
                );

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(employeeDTO)
                .when()
                .post("/employee")
                .then()
                .statusCode(201)
                .body("user.cpf", is(employeeDTO.user().cpf()));
    }

    @Test
    public void updateTest() {
        EmployeeResponseDTO response = createFakeEmployee("111.111");

        UserDTO userDTO =
                new UserDTO(
                        "Usuário Teste Editado",
                        "222.222",
                        "editado@teste.com",
                        "usertesteeditado",
                        "senhatesteeditado",
                        true,
                        LocalDate.now()
                );

        EmployeeDTO employeeDTO =
                new EmployeeDTO(
                        44.0,
                        1530.0,
                        LocalDate.now(),
                        userDTO,
                        Role.MANAGER
                );

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(employeeDTO)
                .when()
                .pathParam("id", response.id())
                .put("/employee/{id}")
                .then()
                .statusCode(204);

        employeeService.delete(response.id());
    }

    @Test
    public void findAllTest() {
        given()
            .when()
            .get("/employee")
            .then()
            .statusCode(200);
    }

    @Test
    public void findByIdTest() {
        EmployeeResponseDTO response = createFakeEmployee("333.333");

        given()
                .when()
                .pathParam("id", response.id())
                .get("/employee/{id}")
                .then()
                .statusCode(200)
                .body("id", is(response.id().intValue()));

        employeeService.delete(response.id());
    }

    @Test
    public void findByNameTest() {
        EmployeeResponseDTO response = createFakeEmployee("444.444");

        given()
                .when()
                .pathParam("name", response.user().name())
                .get("/employee/search/name/{name}")
                .then()
                .statusCode(200)
                .body("user.name", hasItem(response.user().name()));

        employeeService.delete(response.id());
    }

    @Test
    public void deleteTest() {
        EmployeeResponseDTO response = createFakeEmployee("555.555");

        given()
                .when()
                .pathParam("id", response.id())
                .delete("/employee/{id}")
                .then()
                .statusCode(204);
    }

    public EmployeeResponseDTO createFakeEmployee(String cpf) {
        UserDTO userDTO =
                new UserDTO(
                        "Usuário Teste",
                        cpf,
                        "teste@teste.com",
                        "userteste",
                        "senhateste",
                        true,
                        LocalDate.now()
                );

        EmployeeDTO employeeDTO =
                new EmployeeDTO(
                        40.0,
                        1230.0,
                        LocalDate.now(),
                        userDTO,
                        Role.CUSTOMER_SERVICE
                );

        return employeeService.create(employeeDTO);
    }
}
