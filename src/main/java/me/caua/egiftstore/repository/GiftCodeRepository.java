package me.caua.egiftstore.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import me.caua.egiftstore.model.GiftCode;

import java.util.List;

@ApplicationScoped
public class GiftCodeRepository implements PanacheRepository<GiftCode> {

    public List<GiftCode> findByProduto(Long id) {
        return find("from GiftCode where produto.id = ?1", id).list();
    }
}
