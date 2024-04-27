package me.caua.egiftstore.model;

import jakarta.persistence.*;

@Entity
public class GiftCompany extends DefaultEntity {

    @Column()
    private String name;
    @Column()
    private String cnpj;
    @OneToOne(cascade=CascadeType.ALL)
    private Image logo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Image getLogo() {
        return logo;
    }

    public void setLogo(Image logo) {
        this.logo = logo;
    }
}
