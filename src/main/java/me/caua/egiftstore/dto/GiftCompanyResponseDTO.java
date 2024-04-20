package me.caua.egiftstore.dto;

import me.caua.egiftstore.model.GiftCompany;

public record GiftCompanyResponseDTO(
        Long id,
        String name,
        String cnpj,
        ImageResponseDTO logo
) {
    public static GiftCompanyResponseDTO valueOf(GiftCompany giftCompany) {
        return new GiftCompanyResponseDTO(
                giftCompany.getId(),
                giftCompany.getName(),
                giftCompany.getCnpj(),
                ImageResponseDTO.valueOf(giftCompany.getLogo())
        );
    }
}
