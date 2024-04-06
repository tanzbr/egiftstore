package me.caua.egiftstore.model;

import jakarta.persistence.*;
import me.caua.egiftstore.enums.Role;

import java.time.LocalDate;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column()
    private Double weeklyHours;
    @Column()
    private Double salary;
    @Column()
    private LocalDate contractDate;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Enumerated
    private Role role;

}
