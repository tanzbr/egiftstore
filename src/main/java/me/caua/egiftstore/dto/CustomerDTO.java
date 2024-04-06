package me.caua.egiftstore.dto;

import jakarta.persistence.Column;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CustomerDTO(
        LocalDateTime registerDate,
        Boolean acceptMarketing,
        UserDTO userDTO
) {

}
