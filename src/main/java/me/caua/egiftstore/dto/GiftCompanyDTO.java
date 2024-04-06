package me.caua.egiftstore.dto;

import me.caua.egiftstore.model.Image;

public record GiftCompanyDTO(
        String name,
        String cnpj,
        Image image
) {

}
