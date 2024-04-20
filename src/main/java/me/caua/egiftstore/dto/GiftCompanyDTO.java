package me.caua.egiftstore.dto;

public record GiftCompanyDTO(
        String name,
        String cnpj,
        ImageDTO logo
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
