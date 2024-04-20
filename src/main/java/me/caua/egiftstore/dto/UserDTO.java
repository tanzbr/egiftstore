package me.caua.egiftstore.dto;

import java.time.LocalDate;

public record UserDTO(
        String name,
        String cpf,
        String email,
        String username,
        String password,
        Boolean twoFactor,
        LocalDate birthDate
) {

}
