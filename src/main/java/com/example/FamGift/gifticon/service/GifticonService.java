package com.example.FamGift.gifticon.service;

import com.example.FamGift.gifticon.model.Gifticon;
import com.example.FamGift.gifticon.model.GifticonUseYn;
import com.example.FamGift.gifticon.repo.GifticonRepo;
import com.example.FamGift.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GifticonService {
    private final GifticonRepo gifticonRepo;

    public List<Gifticon> getGifticon(User owner) {
        return gifticonRepo.findByGifticonOwnerAndGifticonUseYn(owner, GifticonUseYn.Y);
    }
    public void addGifticon(Gifticon gifticon) {
        gifticonRepo.save(gifticon);
    }
}
