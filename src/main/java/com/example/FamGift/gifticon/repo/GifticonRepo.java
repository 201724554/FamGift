package com.example.FamGift.gifticon.repo;

import com.example.FamGift.gifticon.model.Gifticon;
import com.example.FamGift.gifticon.model.GifticonUseYn;
import com.example.FamGift.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GifticonRepo extends JpaRepository<Gifticon, Long> {
    List<Gifticon> findByGifticonOwnerAndGifticonUseYn(User owner, GifticonUseYn useYn);
}
