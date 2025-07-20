package com.example.FamGift.category.service;

import com.example.FamGift.category.dto.CategoryDto;
import com.example.FamGift.category.model.Category;
import com.example.FamGift.category.repo.CategoryRepo;
import com.example.FamGift.gifticon_category.repo.GifticonCategoryRepo;
import com.example.FamGift.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepo categoryRepo;
    private final GifticonCategoryRepo gifticonCategoryRepo;

    @Transactional
    public void addCategory(CategoryDto dto, User categoryOwner) {
        Category category = new Category(dto, categoryOwner);
        categoryRepo.save(category);
    }

    public List<Category> getCategoriesByCategoryOwner(User categoryOwner) {
        return categoryRepo.findCategoryByCategoryOwnerId(categoryOwner.getId());
    }

    public Optional<Category> getCategoriesByCategoryId(Long id) {
        return categoryRepo.findCategoryById(id);
    }

    @Transactional
    public void updateCategory(Category category, CategoryDto dto) {
        category.updateName(dto.getName());
    }

    @Transactional
    public void deleteCategory(CategoryDto dto) {
        gifticonCategoryRepo.deleteByCategoryId(dto.getId());
        categoryRepo.deleteCategoryById(dto.getId());
    }
}
