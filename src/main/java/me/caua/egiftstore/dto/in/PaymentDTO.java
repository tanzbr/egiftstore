package me.caua.egiftstore.dto.in;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import me.caua.egiftstore.enums.PaymentStatus;
import me.caua.egiftstore.model.OrderItem;
import me.caua.egiftstore.model.Payment;

import java.time.LocalDateTime;
import java.util.List;

public record PaymentDTO(
        @PositiveOrZero(message = "totalPrice cannot be negative")
        Double totalPrice,
        @NotNull(message = "paymentStatus cannot be null")
        PaymentStatus paymentStatus
) {

}
