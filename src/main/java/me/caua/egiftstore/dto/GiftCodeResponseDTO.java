package me.caua.egiftstore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import me.caua.egiftstore.enums.GiftState;
import me.caua.egiftstore.model.GiftCard;
import me.caua.egiftstore.model.GiftCode;

public record GiftCodeResponseDTO(
        Long id,
        String giftCode,
        GiftState giftState,
        GiftCard giftCard
) {

    public static GiftCodeResponseDTO valueOf(GiftCode giftCode) {
        return new GiftCodeResponseDTO(
                giftCode.getId(),
                giftCode.getCode(),
                giftCode.getGiftState(),
                giftCode.getGiftCard()
        );
    }
}
