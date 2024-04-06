package me.caua.egiftstore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import me.caua.egiftstore.model.GiftCode;
import me.caua.egiftstore.model.Image;

import java.util.List;

public record GiftCardDTO(
        @NotBlank(message = "O nome não pode ser nulo ou vazio.") @Size(max = 60, message = "O tamanho máximo do nome é 60 caracteres.")
        String name,
        @Size(max = 5000, message = "O tamanho máximo da descrição é 5000 caracteres.")
        String description,
        @PositiveOrZero(message = "O preço não pode ser negativo.") @NotNull(message = "O preço não pode ser nulo.")
        Double price,
        Integer companyId,
        List<String> tags,
        List<ImageDTO> images,
        List<GiftCodeDTO> giftCodes,
        Boolean visible
) {

    @Override
    public String name() {
        return name;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public Double price() {
        return price;
    }

    @Override
    public Integer companyId() {
        return companyId;
    }

    @Override
    public List<String> tags() {
        return tags;
    }

    @Override
    public List<ImageDTO> images() {
        return images;
    }

    @Override
    public List<GiftCodeDTO> giftCodes() {
        return giftCodes;
    }

    @Override
    public Boolean visible() {
        return visible;
    }
}
