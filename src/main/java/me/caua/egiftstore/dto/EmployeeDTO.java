package me.caua.egiftstore.dto;

import me.caua.egiftstore.enums.Role;

import java.time.LocalDate;

public record EmployeeDTO(
        Double weeklyHours,
        Double salary,
        LocalDate contractDate,
        UserDTO user,
        Role role
) {

}
