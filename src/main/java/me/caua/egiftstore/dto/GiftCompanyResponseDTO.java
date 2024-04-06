package me.caua.egiftstore.dto;

import me.caua.egiftstore.model.Image;

public record GiftCompanyResponseDTO(
        String name,
        String cnpj,
        Image image
) {

}
