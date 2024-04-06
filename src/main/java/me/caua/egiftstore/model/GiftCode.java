package me.caua.egiftstore.model;

import jakarta.persistence.*;
import me.caua.egiftstore.dto.GiftCardDTO;
import me.caua.egiftstore.enums.GiftState;

@Entity
public class GiftCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column()
    private String code;
    @Enumerated
    private GiftState giftState;
    @ManyToOne @JoinColumn(name = "giftcard_id")
    private GiftCard giftCard;

    public GiftCard getGiftCard() {
        return giftCard;
    }

    public void setGiftCard(GiftCard giftCard) {
        this.giftCard = giftCard;
    }

    public GiftCode(String code, GiftState giftState) {
        this.code = code;
        this.giftState = giftState;
    }

    public GiftCode() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public GiftState getGiftState() {
        return giftState;
    }

    public void setGiftState(GiftState giftState) {
        this.giftState = giftState;
    }
}
