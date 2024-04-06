package me.caua.egiftstore.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class GiftCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 60, nullable = false)
    private String name;
    @Column(length = 5000)
    private String description;
    @Column(nullable = false)
    private Double price;
    @Column(length = 1000)
    private List<String> tags;
    @Column()
    private Boolean visible;
    @OneToMany(cascade=CascadeType.ALL)
    private List<GiftCode> giftCodes;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "giftcompany_id")
    private GiftCompany giftCompany;
    @OneToMany(cascade=CascadeType.ALL)
    private List<Image> images;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public List<GiftCode> getGiftCodes() {
        return giftCodes;
    }

    public void setGiftCodes(List<GiftCode> giftCodes) {
        this.giftCodes = giftCodes;
    }

    public GiftCompany getGiftCompany() {
        return giftCompany;
    }

    public void setGiftCompany(GiftCompany giftCompany) {
        this.giftCompany = giftCompany;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
