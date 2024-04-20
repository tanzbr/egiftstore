package me.caua.egiftstore.model.arquivadosA2;

import jakarta.persistence.*;
import me.caua.egiftstore.model.DefaultEntity;
import me.caua.egiftstore.model.User;

import java.time.LocalDateTime;

@Entity
public class Customer extends DefaultEntity {

    @Column()
    private LocalDateTime registerDate;
    @Column()
    private Boolean acceptMarketing;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
