package com.example.FamGift.category.dto;

import com.example.FamGift.category.model.Category;
import com.example.FamGift.user.model.User;
import lombok.Getter;

@Getter
public class CategoryDto {
    public CategoryDto() {}
    public CategoryDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }

    private Long id;
    private String name;
}
