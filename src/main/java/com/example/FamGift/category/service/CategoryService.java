package com.example.FamGift.category.service;

import com.example.FamGift.category.dto.CategoryDto;
import com.example.FamGift.category.model.Category;
import com.example.FamGift.category.repo.CategoryRepo;
import com.example.FamGift.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepo categoryRepo;

    @Transactional
    public void addCategory(CategoryDto dto, User categoryOwner) {
        Category category = new Category(dto, categoryOwner);
        categoryRepo.save(category);
    }

    @Transactional
    public List<Category> getCategoriesByCategoryOwner(User categoryOwner) {
        return categoryRepo.findCategoryByCategoryOwnerId(categoryOwner.getId());
    }
}
