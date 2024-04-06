package me.caua.egiftstore.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import me.caua.egiftstore.dto.GiftCardDTO;
import me.caua.egiftstore.dto.GiftCardResponseDTO;
import me.caua.egiftstore.model.GiftCard;
import me.caua.egiftstore.model.GiftCode;
import me.caua.egiftstore.model.Image;
import me.caua.egiftstore.repository.GiftCardRepository;
import me.caua.egiftstore.repository.GiftCodeRepository;

import java.util.List;

@ApplicationScoped
public class GiftCardServiceImpl implements GiftCardService {

    @Inject
    public GiftCardRepository giftCardRepository;
    @Inject
    public GiftCodeRepository giftCodeRepository;

    @Override
    @Transactional
    public GiftCardResponseDTO create(@Valid GiftCardDTO giftCardDTO) {
        GiftCard giftCard = new GiftCard();
        giftCard.setName(giftCardDTO.name());
        giftCard.setDescription(giftCardDTO.description());
        giftCard.setPrice(giftCardDTO.price());
        giftCard.setTags(giftCardDTO.tags());
        giftCard.setVisible(giftCardDTO.visible());
        giftCard.setImages(giftCardDTO.images().stream().map(Image::valueOf).toList());
        giftCard.setGiftCodes(giftCardDTO.giftCodes().stream()
                .map(giftCodeDTO -> new GiftCode(giftCodeDTO.giftCode(), giftCodeDTO.giftState(), giftCard)).toList());

        // to-do giftcompany

        giftCardRepository.persist(giftCard);

        return GiftCardResponseDTO.valueOf(giftCard);
    }

    @Override
    @Transactional
    public void update(Long id, @Valid GiftCardDTO giftCardDTO) {
        GiftCard giftCard = giftCardRepository.findById(id);

        giftCard.setName(giftCardDTO.name());
        giftCard.setDescription(giftCardDTO.description());
        giftCard.setPrice(giftCardDTO.price());
        giftCard.setTags(giftCardDTO.tags());
        giftCard.setVisible(giftCardDTO.visible());
        giftCard.setImages(giftCardDTO.images().stream().map(Image::valueOf).toList());

        giftCard.setGiftCodes(giftCardDTO.giftCodes().stream()
                .map(giftCodeDTO -> new GiftCode(giftCodeDTO.giftCode(), giftCodeDTO.giftState(), giftCard)).toList());

        // to-do giftcompany
    }

    @Override
    @Transactional
    public void delete(Long id) {
        giftCardRepository.deleteById(id);
    }

    @Override
    public GiftCardResponseDTO findById(Long id) {
        return null;
    }

    @Override
    public List<GiftCardResponseDTO> findAll() {
        return giftCardRepository.listAll()
                .stream()
                .map(GiftCardResponseDTO::valueOf)
                .toList();
    }

    @Override
    public List<GiftCardResponseDTO> findByName(String name) {
        return giftCardRepository.findByName(name)
                .stream()
                .map(GiftCardResponseDTO::valueOf)
                .toList();
    }


}
