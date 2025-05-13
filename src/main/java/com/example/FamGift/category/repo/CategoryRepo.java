package com.example.FamGift.category.repo;

import com.example.FamGift.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    List<Category> findCategoryByCategoryOwnerId(Long categoryOwnerId);
}
