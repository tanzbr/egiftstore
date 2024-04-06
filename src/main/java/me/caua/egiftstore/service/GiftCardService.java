package me.caua.egiftstore.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import me.caua.egiftstore.dto.GiftCardDTO;
import me.caua.egiftstore.dto.GiftCardResponseDTO;

import java.util.List;

@ApplicationScoped
public interface GiftCardService {

    GiftCardResponseDTO create(@Valid GiftCardDTO giftCardDTO);
    void update(Long id, @Valid GiftCardDTO produtoDTO);
    void delete(Long id);
    GiftCardResponseDTO findById(Long id);
    List<GiftCardResponseDTO> findByName(String name);
    List<GiftCardResponseDTO> findAll();

}
