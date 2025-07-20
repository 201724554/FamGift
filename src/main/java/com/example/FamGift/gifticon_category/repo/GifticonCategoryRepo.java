package com.example.FamGift.gifticon_category.repo;

import com.example.FamGift.category.model.Category;
import com.example.FamGift.gifticon.model.Gifticon;
import com.example.FamGift.gifticon_category.model.GifticonCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GifticonCategoryRepo extends JpaRepository<GifticonCategory, Long> {
    List<GifticonCategory> findByCategory(Category category);
    List<GifticonCategory> findByCategoryId(Long category);
    List<GifticonCategory> findByGifticon(Gifticon gifticonId);
    List<GifticonCategory> findByGifticonId(Long gifticonId);
    List<GifticonCategory> findByGifticonAndCategory(Gifticon gifticon, Category category);
    void deleteByGifticon(Gifticon gifticon);
    void deleteByGifticonId(Long gifticon);
    void deleteByCategoryId(Long categoryId);
    void deleteByCategoryId(Category category);
}
