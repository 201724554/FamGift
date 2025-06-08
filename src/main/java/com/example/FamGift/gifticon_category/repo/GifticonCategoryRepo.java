package com.example.FamGift.gifticon_category.repo;

import com.example.FamGift.gifticon_category.model.GifticonCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GifticonCategoryRepo extends JpaRepository<GifticonCategory, Long> {
}
