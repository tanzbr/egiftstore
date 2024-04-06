package me.caua.egiftstore.dto;

import me.caua.egiftstore.model.Image;

public record GiftCompanyDTO(
        String name,
        String cnpj,
        Image image
) {
    @Override
    public String name() {
        return name;
    }

    @Override
    public String cnpj() {
        return cnpj;
    }

    @Override
    public Image image() {
        return image;
    }
}
