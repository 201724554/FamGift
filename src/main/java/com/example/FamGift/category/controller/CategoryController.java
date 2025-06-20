package com.example.FamGift.category.controller;

import com.example.FamGift.category.dto.CategoryDto;
import com.example.FamGift.category.facade.CategoryFacade;
import com.example.FamGift.category.model.Category;
import com.example.FamGift.common.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoryController implements BaseController {
    private final CategoryFacade categoryFacade;

    @PostMapping("/category")
    private ResponseEntity<String> addCategory(@RequestBody CategoryDto dto) {
       categoryFacade.addCategory(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/category")
    private ResponseEntity<List<CategoryDto>> getCategories() {
        List<CategoryDto> categories = categoryFacade.getCategoriesByCategoryOwner();
        return ResponseEntity.ok(categories);
    }

    @DeleteMapping("")
    private ResponseEntity<?> deleteCategory() {
        return null;
    }
}
