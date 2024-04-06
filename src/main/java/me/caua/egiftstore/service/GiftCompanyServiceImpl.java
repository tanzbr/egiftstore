package me.caua.egiftstore.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import me.caua.egiftstore.dto.GiftCodeDTO;
import me.caua.egiftstore.dto.GiftCodeResponseDTO;
import me.caua.egiftstore.dto.GiftCompanyDTO;
import me.caua.egiftstore.dto.GiftCompanyResponseDTO;
import me.caua.egiftstore.model.GiftCode;
import me.caua.egiftstore.model.GiftCompany;
import me.caua.egiftstore.repository.GiftCardRepository;
import me.caua.egiftstore.repository.GiftCodeRepository;
import me.caua.egiftstore.repository.GiftCompanyRepository;
import me.caua.egiftstore.validation.ValidationException;

import java.util.List;

@ApplicationScoped
public class GiftCompanyServiceImpl implements GiftCompanyService{

    @Inject
    public GiftCardRepository produtoRepository;
    @Inject
    public GiftCodeRepository giftCodeRepository;
    @Inject
    public GiftCompanyRepository giftCompanyRepository;

    @Override
    @Transactional
    public GiftCompanyResponseDTO create(@Valid GiftCompanyDTO giftCompanyDTO) {

        GiftCompany giftCompany = new GiftCompany();
        giftCompany.setName(giftCompanyDTO.name());
        giftCompany.setCnpj(giftCompanyDTO.cnpj());

        giftCompanyRepository.persist(giftCompany);

        return GiftCompanyResponseDTO.valueOf(giftCompany);
    }

    @Override
    @Transactional
    public void update(Long id, @Valid GiftCompanyDTO giftCompanyDTO) {

        GiftCompany giftCompany = giftCompanyRepository.findById(id);
        giftCompany.setName(giftCompanyDTO.name());
        giftCompany.setCnpj(giftCompanyDTO.cnpj());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        giftCompanyRepository.deleteById(id);
    }

    @Override
    public GiftCompanyResponseDTO findById(Long id) {
        return GiftCompanyResponseDTO.valueOf(giftCompanyRepository.findById(id));
    }

    @Override
    public List<GiftCompanyResponseDTO> findByName(String name) {
        return giftCompanyRepository.findByName(name)
                .stream()
                .map(GiftCompanyResponseDTO::valueOf)
                .toList();
    }

    @Override
    public List<GiftCompanyResponseDTO> findAll() {
        return giftCompanyRepository.listAll()
                .stream()
                .map(GiftCompanyResponseDTO::valueOf)
                .toList();
    }

}
