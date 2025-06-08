package com.example.FamGift.gifticon_category.service;

import com.example.FamGift.gifticon_category.model.GifticonCategory;
import com.example.FamGift.gifticon_category.repo.GifticonCategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GifticonCategoryService {
    private final GifticonCategoryRepo gifticonCategoryRepo;
    public void addGifticonCategories(List<GifticonCategory> gifticonCategoryList) {
        gifticonCategoryRepo.saveAll(gifticonCategoryList);
    }
}
