package me.caua.egiftstore.dto;

import java.time.LocalDate;

public record EmployeeDTO(
        Double weeklyHours,
        Double salary,
        LocalDate contractDate,
        UserDTO userDTO
) {

}
