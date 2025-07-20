package com.example.FamGift.category.facade;

import com.example.FamGift.category.dto.CategoryDto;
import com.example.FamGift.category.model.Category;
import com.example.FamGift.category.service.CategoryService;
import com.example.FamGift.common.service.CommonService;
import com.example.FamGift.common.service.JwtTokenServiceImpl;
import com.example.FamGift.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryFacade {
    private final CategoryService categoryService;
    private final CommonService commonService;

    @Transactional
    public void addCategory(CategoryDto dto) {
        User categoryOwner = commonService.findUserByJwtToken();
        categoryService.addCategory(dto, categoryOwner);
    }

    public List<CategoryDto> getCategoriesByCategoryOwner() {
        User categoryOwner = commonService.findUserByJwtToken();
        List<Category> categories = categoryService.getCategoriesByCategoryOwner(categoryOwner);
        return categories.stream().map(CategoryDto::new).collect(Collectors.toList());
    }

    public void updateCategoryName(CategoryDto dto) {
        Category category = categoryService.getCategoriesByCategoryId(dto.getId()).orElseThrow(NoSuchElementException::new);
        categoryService.updateCategory(category, dto);
    }

    public void deleteCategory(CategoryDto dto) {
        categoryService.deleteCategory(dto);
    }
}
