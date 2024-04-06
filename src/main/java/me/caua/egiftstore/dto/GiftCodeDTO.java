package me.caua.egiftstore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import me.caua.egiftstore.enums.GiftState;

public record GiftCodeDTO(
        @NotBlank(message = "O código do gift não pode ser nulo ou vazio.") @Size(max = 150, message = "O tamanho máximo do gift é 150 caracteres.")
        String giftCode,
        GiftState giftState,
        Long produtoId
) {
}
