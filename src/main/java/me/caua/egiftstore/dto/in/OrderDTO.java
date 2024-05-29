package me.caua.egiftstore.dto.in;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import me.caua.egiftstore.model.OrderItem;
import me.caua.egiftstore.model.Payment;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDTO(
        @NotNull(message = "orderDate cannot be null.")
        LocalDateTime orderDate,
        @NotNull(message = "customerId cannot be null.")
        Long customerId,
        @NotNull(message = "items cannot be null.")
        List<OrderItemDTO> items,
        @NotNull(message = "payment cannot be null.")
        PaymentDTO paymentDTO
) {

}
