package com.example.dz7Api.controller;

import org.springframework.web.bind.annotation.*;

import com.example.dz7Api.models.Category;
import com.example.dz7Api.service.CategoryService;

import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;


@RestController
@RequestMapping("/api/category")
public class CategoryController {
    
    List<Category> categories = new ArrayList<>();

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> listCategories (Model model) {
        if (categories.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{categoryName}")
    public ResponseEntity<Category> getCategory(@PathVariable String categoryName) {
        if (categoryName == null || categoryName.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Category> foundCategory = categories.stream()
        .filter(category -> category.getCategoryName().equalsIgnoreCase(categoryName))
        .findFirst();

        return foundCategory.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Category> saveCategory(@RequestBody Category categories) {
        try {
            Category savedCategory = categoryService.saveCategory(categories);
            return ResponseEntity.ok(savedCategory);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        try {
            Category category = categoryService.getCategoryById(id);
            return ResponseEntity.ok(category);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

}
