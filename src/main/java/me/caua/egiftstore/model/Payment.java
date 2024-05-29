package me.caua.egiftstore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import me.caua.egiftstore.enums.PaymentStatus;

@Entity
public class Payment extends DefaultEntity {

    @Column()
    private Double totalPrice;
    @Enumerated
    private PaymentStatus paymentStatus;

    public Payment() {
    }

    public Payment(Double totalPrice, PaymentStatus paymentStatus) {
        this.totalPrice = totalPrice;
        this.paymentStatus = paymentStatus;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
