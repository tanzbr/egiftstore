package me.caua.egiftstore.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import me.caua.egiftstore.dto.GiftCodeDTO;
import me.caua.egiftstore.dto.GiftCodeResponseDTO;
import me.caua.egiftstore.enums.GiftState;
import me.caua.egiftstore.model.GiftCard;
import me.caua.egiftstore.model.GiftCode;
import me.caua.egiftstore.repository.GiftCardRepository;
import me.caua.egiftstore.repository.GiftCodeRepository;
import me.caua.egiftstore.validation.ValidationException;

import java.util.List;

@ApplicationScoped
public class GiftCodeServiceImpl implements GiftCodeService{

    @Inject
    public GiftCardRepository produtoRepository;
    @Inject
    public GiftCodeRepository giftCodeRepository;

    @Override
    @Transactional
    public GiftCodeResponseDTO create(@Valid GiftCodeDTO giftCodeDTO) {
        // validate if product exists
        validateProductExists(giftCodeDTO.produtoId());

        GiftCode giftCode = new GiftCode();
        giftCode.setCode(giftCodeDTO.giftCode());
        giftCode.setGiftState(giftCodeDTO.giftState());
        giftCode.setGiftCard(produtoRepository.findById(giftCodeDTO.produtoId()));

        giftCodeRepository.persist(giftCode);

        return GiftCodeResponseDTO.valueOf(giftCode);
    }

    @Override
    @Transactional
    public void update(Long id, @Valid GiftCodeDTO giftCodeDTO) {
        // validate if product exists
        validateProductExists(giftCodeDTO.produtoId());

        GiftCode giftCode = giftCodeRepository.findById(id);

        giftCode.setCode(giftCodeDTO.giftCode());
        giftCode.setGiftState(giftCodeDTO.giftState());
        giftCode.setGiftCard(produtoRepository.findById(giftCodeDTO.produtoId()));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        giftCodeRepository.deleteById(id);
    }

    @Override
    public GiftCodeResponseDTO findById(Long id) {
        return GiftCodeResponseDTO.valueOf(giftCodeRepository.findById(id));
    }

    @Override
    public List<GiftCodeResponseDTO> findByProduto(Long id) {
        return giftCodeRepository.findByProduto(id)
                .stream()
                .map(GiftCodeResponseDTO::valueOf)
                .toList();
    }

    @Override
    public List<GiftCodeResponseDTO> findAll() {
        return giftCodeRepository.listAll()
                .stream()
                .map(GiftCodeResponseDTO::valueOf)
                .toList();
    }

    public void validateProductExists(Long produtoId) {
        if (produtoRepository.findById(produtoId) == null)
            throw new ValidationException("produtoId", "Não existe um produto com este ID.");
    }
}
