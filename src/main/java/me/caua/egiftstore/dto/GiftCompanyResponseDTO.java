package me.caua.egiftstore.dto;

import me.caua.egiftstore.model.GiftCompany;
import me.caua.egiftstore.model.Image;

public record GiftCompanyResponseDTO(
        String name,
        String cnpj
) {
    public static GiftCompanyResponseDTO valueOf(GiftCompany giftCompany) {
        return new GiftCompanyResponseDTO(
                giftCompany.getName(),
                giftCompany.getCnpj()
        );
    }
}
