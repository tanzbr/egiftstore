package me.caua.egiftstore.dto.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GiftCompanyDTO(
        @NotBlank(message = "name cannot be null or empty.")
        String name,
        @NotBlank(message = "cnpj cannot be null or empty.")
        String cnpj,
        @NotNull(message = "logo cannot be null.")
        ImageDTO logo
) {
}
