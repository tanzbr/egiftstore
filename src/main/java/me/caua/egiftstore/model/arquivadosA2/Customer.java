package me.caua.egiftstore.model.arquivadosA2;

import jakarta.persistence.*;
import me.caua.egiftstore.model.DefaultEntity;
import me.caua.egiftstore.model.User;

@Entity
public class Customer extends DefaultEntity {
    @Column()
    private Boolean acceptMarketing;
    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
