package me.caua.egiftstore.dto;

import me.caua.egiftstore.model.GiftCard;
import me.caua.egiftstore.model.Image;

public record ImageResponseDTO(
        Long id,
        String caption,
        String url,
        Integer priority
) {
    public static ImageResponseDTO valueOf(Image image) {
        return new ImageResponseDTO(
                image.getId(),
                image.getCaption(),
                image.getUrl(),
                image.getPriority());
    }
}
