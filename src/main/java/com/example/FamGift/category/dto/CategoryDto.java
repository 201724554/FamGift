package com.example.FamGift.category.dto;

import com.example.FamGift.user.model.User;
import lombok.Getter;

@Getter
public class CategoryDto {
    private Long id;
    private String name;
    private User categoryOwner;
}
