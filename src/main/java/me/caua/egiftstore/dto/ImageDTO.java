package me.caua.egiftstore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ImageDTO(
        String caption,
        String url,
        Integer priority
) {

}
