package me.caua.egiftstore.utils;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import me.caua.egiftstore.dto.in.EmployeeDTO;
import me.caua.egiftstore.dto.in.GiftCompanyDTO;
import me.caua.egiftstore.dto.in.UserDTO;
import me.caua.egiftstore.dto.out.GiftCompanyResponseDTO;
import me.caua.egiftstore.dto.out.UserResponseDTO;
import me.caua.egiftstore.enums.Role;
import me.caua.egiftstore.repository.EmployeeRepository;
import me.caua.egiftstore.service.EmployeeService;
import me.caua.egiftstore.service.GiftCompanyService;
import me.caua.egiftstore.service.HashService;
import me.caua.egiftstore.service.JwtService;

import java.time.LocalDate;

@ApplicationScoped
public class TestUtils {

    @Inject
    EmployeeService employeeService;
    @Inject
    EmployeeRepository employeeRepository;
    @Inject
    HashService hashService;
    @Inject
    JwtService jwtService;
    @Inject
    GiftCompanyService giftCompanyService;

    public String getAuth() {
        UserResponseDTO userResponseDTO = null;

        if (employeeRepository.findByCpf("111-TESTE") != null) {
            userResponseDTO = employeeService.login("tanz@gmail.com", hashService.getHashSenha("admin"));
        } else {
            UserDTO userDTO = new UserDTO(
                    "Darius Tanz",
                    "111-TESTE",
                    "tanz@gmail.com",
                    "tanz",
                    "admin",
                    false,
                    LocalDate.now()
            );

            userResponseDTO = employeeService.create(new EmployeeDTO(
                    40.0,
                    1200.0,
                    LocalDate.now(),
                    userDTO,
                    Role.CEO
            )).user();
        }

        System.out.println(userResponseDTO);
        return jwtService.generateJwt(userResponseDTO, 0);
    }

    public GiftCompanyResponseDTO createFakeCompany() {
        GiftCompanyDTO giftCompanyDTO
                = new GiftCompanyDTO("Teste", "111.111.111");

        return giftCompanyService.create(giftCompanyDTO);
    }

}
