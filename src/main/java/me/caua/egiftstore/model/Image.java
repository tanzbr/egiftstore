package me.caua.egiftstore.model;

import jakarta.persistence.*;
import me.caua.egiftstore.dto.ImageDTO;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column()
    private String caption;
    @Column()
    private String url;
    @Column()
    private Integer priority;

    public Image(String caption, String url, Integer priority) {
        this.caption = caption;
        this.url = url;
        this.priority = priority;
    }

    public Image() {
    }

    public static Image valueOf(ImageDTO imageDTO) {
        return new Image(
                imageDTO.caption(),
                imageDTO.url(),
                imageDTO.priority()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
