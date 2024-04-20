package me.caua.egiftstore.model.arquivadosA2;

import jakarta.persistence.*;
import me.caua.egiftstore.model.DefaultEntity;

import java.time.LocalDateTime;
import java.util.List;

@Entity @Table(name="Order_")
public class Order extends DefaultEntity {

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
