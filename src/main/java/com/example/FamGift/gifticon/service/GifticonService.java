package com.example.FamGift.gifticon.service;

import com.example.FamGift.gifticon.model.Gifticon;
import com.example.FamGift.gifticon.model.GifticonUseYn;
import com.example.FamGift.gifticon.repo.GifticonRepo;
import com.example.FamGift.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GifticonService {
    private final GifticonRepo gifticonRepo;

    public List<Gifticon> getGifticon(User owner) {
        return gifticonRepo.findByGifticonOwnerAndGifticonUseYn(owner, GifticonUseYn.Y);
    }

    public Optional<Gifticon> getGifticon(Long id) {
        return gifticonRepo.findById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveGifticon(Gifticon gifticon) {
        gifticonRepo.save(gifticon);
    }

}
