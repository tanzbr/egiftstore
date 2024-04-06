package me.caua.egiftstore.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity @Table(name="User_")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column()
    private String name;
    @Column()
    private String cpf;
    @Column()
    private String email;
    @Column()
    private String username;
    @Column()
    private String password;
    @Column()
    private Boolean twoFactor;
    @Column()
    private LocalDate birthDate;

}
