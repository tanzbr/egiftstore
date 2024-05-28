package me.caua.egiftstore.model;

import jakarta.persistence.*;
import me.caua.egiftstore.model.DefaultEntity;
import me.caua.egiftstore.model.GiftCard;

@Entity
public class OrderItem extends DefaultEntity {

    @Column()
    private String name;
    @Column()
    private Double unitPrice;
    @Column()
    private Integer quantity;
    @OneToOne
    @JoinColumn(name = "giftcard_id")
    private GiftCard giftCard;

}
