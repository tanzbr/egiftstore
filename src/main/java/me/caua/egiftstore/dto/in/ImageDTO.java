package me.caua.egiftstore.dto.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record ImageDTO(
        @NotBlank(message = "caption cannot be null or empty.")
        String caption,
        @NotBlank(message = "url cannot be null or empty.")
        String url,
        @NotNull(message = "priority cannot be null or empty.") @PositiveOrZero(message = "priority cannot be negative")
        Integer priority
) {

}
