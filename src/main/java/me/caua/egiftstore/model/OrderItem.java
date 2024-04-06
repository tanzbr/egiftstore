package me.caua.egiftstore.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column()
    private Double unitPrice;
    @Column()
    private Integer quantity;
    @OneToOne
    @JoinColumn(name = "giftcard_id")
    private GiftCard giftCard;

}
