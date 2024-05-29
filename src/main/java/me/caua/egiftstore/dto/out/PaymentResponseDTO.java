package me.caua.egiftstore.dto.out;

import me.caua.egiftstore.enums.PaymentStatus;
import me.caua.egiftstore.model.Payment;

public record PaymentResponseDTO(
        Long id,
        Double totalPrice,
        PaymentStatus paymentStatus
) {

    public static PaymentResponseDTO valueOf(Payment payment) {
        return new PaymentResponseDTO(
                payment.getId(),
                payment.getTotalPrice(),
                payment.getPaymentStatus()
        );
    }
}
