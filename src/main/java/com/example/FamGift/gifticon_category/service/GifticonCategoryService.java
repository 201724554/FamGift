package com.example.FamGift.gifticon_category.service;

import com.example.FamGift.category.model.Category;
import com.example.FamGift.gifticon.model.Gifticon;
import com.example.FamGift.gifticon_category.model.GifticonCategory;
import com.example.FamGift.gifticon_category.repo.GifticonCategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GifticonCategoryService {
    private final GifticonCategoryRepo gifticonCategoryRepo;

    public List<GifticonCategory> getGifticonCategoriesByGifticon(Gifticon gifticon) {
        return gifticonCategoryRepo.findByGifticon(gifticon);
    }

    public List<GifticonCategory> getGifticonCategoriesByGifticon(Long gifticonId) {
        return gifticonCategoryRepo.findByGifticonId(gifticonId);
    }

    public List<GifticonCategory> getGifticonCategoriesByCategory(Category category) {
        return gifticonCategoryRepo.findByCategory(category);
    }

    public List<GifticonCategory> getGifticonCategoriesByCategory(Long categoryId) {
        return gifticonCategoryRepo.findByCategoryId(categoryId);
    }

    public List<GifticonCategory> getGifticonCategoriesByGifticonAndCategory(Category category, Gifticon gifticon) {
        return gifticonCategoryRepo.findByGifticonAndCategory(gifticon, category);
    }

    @Transactional
    public void deleteGifticonCategoryByGifticon(Gifticon gifticon) {
        gifticonCategoryRepo.deleteByGifticon(gifticon);
    }

    @Transactional
    public void deleteGifticonCategoryByGifticon(Long gifticonId) {
        gifticonCategoryRepo.deleteByGifticonId(gifticonId);
    }

    @Transactional
    public void addGifticonCategories(List<GifticonCategory> gifticonCategoryList) {
        gifticonCategoryRepo.saveAll(gifticonCategoryList);
    }
}
