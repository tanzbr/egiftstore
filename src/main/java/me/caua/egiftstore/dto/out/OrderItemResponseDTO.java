package me.caua.egiftstore.dto.out;

import me.caua.egiftstore.model.GiftCard;
import me.caua.egiftstore.model.OrderItem;

public record OrderItemResponseDTO(
        Long id,
        Double price,
        Double discount,
        Integer quantity,
        GiftCardResponseDTO giftCardResponseDTO
) {
    public static OrderItemResponseDTO valueOf(OrderItem orderItem) {
        return new OrderItemResponseDTO(
                orderItem.getId(),
                orderItem.getPrice(),
                orderItem.getDiscount(),
                orderItem.getQuantity(),
                GiftCardResponseDTO.valueOf(orderItem.getGiftCard()));
    }
}
