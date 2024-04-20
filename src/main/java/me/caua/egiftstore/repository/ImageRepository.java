package me.caua.egiftstore.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import me.caua.egiftstore.model.Image;

import java.util.List;

@ApplicationScoped
public class ImageRepository implements PanacheRepository<Image> {

    public List<Image> findByCaption(String caption) {
        return find("UPPER(caption) LIKE UPPER(?1)", "%"+caption+"%").list();
    }

}
