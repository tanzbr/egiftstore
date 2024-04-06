package me.caua.egiftstore.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column()
    private LocalDateTime registerDate;
    @Column()
    private Boolean acceptMarketing;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
