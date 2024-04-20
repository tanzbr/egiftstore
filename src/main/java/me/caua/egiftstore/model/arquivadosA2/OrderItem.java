package me.caua.egiftstore.model.arquivadosA2;

import jakarta.persistence.*;
import me.caua.egiftstore.model.DefaultEntity;
import me.caua.egiftstore.model.GiftCard;

@Entity
public class OrderItem extends DefaultEntity {

    @Column()
    private Double unitPrice;
    @Column()
    private Integer quantity;
    @OneToOne
    @JoinColumn(name = "giftcard_id")
    private GiftCard giftCard;

}
