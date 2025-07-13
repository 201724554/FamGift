package com.example.FamGift.gifticon.repo;

import com.example.FamGift.common.model.CommonYn;
import com.example.FamGift.gifticon.model.Gifticon;
import com.example.FamGift.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GifticonRepo extends JpaRepository<Gifticon, Long> {
    List<Gifticon> findByGifticonOwnerAndGifticonUseYn(User owner, CommonYn useYn);
    //카테고리를 여러 개 선택하면 모든 카레고리를 포함하는 경우에만 조회가 돼야 하는데 OR 방식으로 조회함
    @Query("SELECT DISTINCT g FROM " +
            "Gifticon g " +
            "JOIN g.gifticonCategories gc " +
            "JOIN gc.category c " +
            "WHERE g.gifticonOwner = :owner " +
            "AND c.id = :categoryIds " +
            "AND g.gifticonUseYn = :gifticonUseYn")
    List<Gifticon> findByOwnerIdAndCategoryIds(
            @Param("owner") User owner,
            @Param("categoryIds") Long categoryId,
            @Param("gifticonUseYn") CommonYn gifticonUseYn);

}
