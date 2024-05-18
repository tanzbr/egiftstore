package me.caua.egiftstore.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import me.caua.egiftstore.dto.in.GiftCompanyDTO;
import me.caua.egiftstore.dto.out.GiftCompanyResponseDTO;

import java.util.List;

@ApplicationScoped
public interface GiftCompanyService {

    GiftCompanyResponseDTO create(@Valid GiftCompanyDTO giftCompanyDTO);
    void update(Long id, @Valid GiftCompanyDTO giftCompanyDTO);
    void delete(Long id);
    GiftCompanyResponseDTO findById(Long id);
    List<GiftCompanyResponseDTO> findByName(String name);
    List<GiftCompanyResponseDTO> findAll();

}
