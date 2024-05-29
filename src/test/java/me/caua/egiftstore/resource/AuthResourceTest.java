package me.caua.egiftstore.resource;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import me.caua.egiftstore.dto.in.AuthUserDTO;
import me.caua.egiftstore.dto.in.EmployeeDTO;
import me.caua.egiftstore.dto.in.UserDTO;
import me.caua.egiftstore.dto.out.CustomerResponseDTO;
import me.caua.egiftstore.dto.out.EmployeeResponseDTO;
import me.caua.egiftstore.enums.Role;
import me.caua.egiftstore.service.EmployeeService;
import me.caua.egiftstore.utils.TestUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class AuthResourceTest {

    @Inject
    EmployeeService employeeService;
    @Inject
    TestUtils testUtils;

    @Test
    public void authTestEmployee() {
        long id = createEmployee().id();

        AuthUserDTO authUserDTO = new AuthUserDTO(
                "teste@teste.com",
                "senhateste",
                0
        );

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(authUserDTO)
                .when()
                .post("/auth")
                .then()
                .statusCode(200);

        employeeService.delete(id);
    }

    private EmployeeResponseDTO createEmployee() {
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
                        Role.SALES
                );

        return employeeService.create(employeeDTO);
    }

//    private CustomerResponseDTO getCustomer() {
//
//    }
}
