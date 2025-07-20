package com.example.FamGift.category.controller;

import com.example.FamGift.category.dto.CategoryDto;
import com.example.FamGift.category.facade.CategoryFacade;
import com.example.FamGift.category.model.Category;
import com.example.FamGift.common.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/category")
    private ResponseEntity<String> updateCategories(@RequestBody CategoryDto dto) {
        categoryFacade.updateCategoryName(dto);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/category")
    private ResponseEntity<String> deleteCategory(@RequestBody CategoryDto dto) {
        categoryFacade.deleteCategory(dto);
        return ResponseEntity.ok(null);
    }
}
