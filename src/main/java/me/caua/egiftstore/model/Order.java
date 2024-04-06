package me.caua.egiftstore.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column()
    private Double totalPrice;
    @Column()
    private LocalDateTime orderDate;
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToMany
    private List<OrderItem> orderItems;
    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

}
