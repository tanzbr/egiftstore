package me.caua.egiftstore.model;

import jakarta.persistence.*;
import me.caua.egiftstore.enums.PaymentStatus;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column()
    private Double totalPrice;
    @Enumerated
    private PaymentStatus paymentStatus;

}
