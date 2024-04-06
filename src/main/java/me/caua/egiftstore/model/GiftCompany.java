package me.caua.egiftstore.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class GiftCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column()
    private String name;
    @Column()
    private String cnpj;
    @ManyToOne
    @JoinColumn(name = "giftcard_id")
    private GiftCard giftCard;

}
