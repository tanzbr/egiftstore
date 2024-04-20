package me.caua.egiftstore.dto;

import me.caua.egiftstore.model.GiftCard;

import java.util.List;

public record GiftCardResponseDTO(
        Long id,
        String name,
        String description,
        Double price,
        List<String> tags,
        List<ImageResponseDTO> images,
        Boolean visible,
        GiftCompanyResponseDTO company,
        List<GiftCodeResponseDTO> giftCodes
) {
    public static GiftCardResponseDTO valueOf(GiftCard giftCard) {
        return new GiftCardResponseDTO(
                giftCard.getId(),
                giftCard.getName(),
                giftCard.getDescription(),
                giftCard.getPrice(),
                giftCard.getTags(),
                giftCard.getImages().stream().map(ImageResponseDTO::valueOf).toList(),
                giftCard.getVisible(),
                GiftCompanyResponseDTO.valueOf(giftCard.getGiftCompany()),
                giftCard.getGiftCodes().stream().map(GiftCodeResponseDTO::valueOf).toList());
    }
}
