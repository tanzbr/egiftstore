package me.caua.egiftstore.model.arquivadosA2;

import jakarta.persistence.*;
import me.caua.egiftstore.enums.PaymentStatus;
import me.caua.egiftstore.model.DefaultEntity;

@Entity
public class Payment extends DefaultEntity {

    @Column()
    private Double totalPrice;
    @Enumerated
    private PaymentStatus paymentStatus;

}
