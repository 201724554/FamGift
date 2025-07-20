package com.example.FamGift.category.repo;

import com.example.FamGift.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    Optional<Category> findCategoryById(Long id);
    List<Category> findCategoryByCategoryOwnerId(Long categoryOwnerId);
    void deleteCategoryById(Long id);
}
